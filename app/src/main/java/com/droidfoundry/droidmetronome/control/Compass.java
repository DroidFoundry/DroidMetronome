package com.droidfoundry.droidmetronome.control;

import android.app.IntentService;
import android.content.Intent;
import android.os.Looper;
import com.droidfoundry.droidmetronome.model.InputLimit;
import com.droidfoundry.droidmetronome.model.RhythmFigure;
import com.droidfoundry.droidmetronome.model.SoundTemplate;
import com.droidfoundry.droidmetronome.model.UserInterface;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by pedro on 12/05/15.
 * Executa o som durante determinando tempo
 */
public class Compass extends IntentService {

    private long frequencyBpm;
    private int timeInMinute;
    private int beatsMax;
    private RhythmFigure rhythmFigure;
    private SoundTemplate soundTemplate;
    private SoundTimeLoop timer;

    /**
     * Construtor da classe compasso
     */
    public Compass() {
        super("Compass");
    }

    /**
     * Método executado ao iniciar o service
     */
    @Override
    public void onCreate() {

        super.onCreate();

        UserInterface userInterface = EventBus.getDefault().removeStickyEvent(UserInterface.class);

        // Configurações de som no geral
        RhythmFigure rhythmFig = userInterface.getRhythmFigure();
        int minTimeInMinutes = userInterface.getTimeInMinutes();
        long frequencyBpm = userInterface.getFrequencyBPM();
        int numberBeats = userInterface.getBeatsQuantity();

        this.setSoundConfiguration(rhythmFig, minTimeInMinutes, frequencyBpm, numberBeats);
        this.soundTemplate = userInterface.getSound();

        // Configuraçoes luz e vibração
        boolean vibrating = userInterface.isVibration();
        boolean lighting = userInterface.isFlash();

        // Definindo configurações de hardware
        HardwareActions.getInstance().setHardwareConfiguration(vibrating, lighting, getApplicationContext());
        HardwareActions.getInstance().startConfiguration();
    }

    /**
     * Método que será executado ao startar o service
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        double secondFrequency = this.conversorBPM(this.frequencyBpm); // 2bps
        long delay = 1000 / (long) secondFrequency; // 0.5s
        int timeMilliSecond = this.timeInMinute * 60 * 1000; // 60000 milisegundos

        // Iniciando ciclo de batidas
        timer = new SoundTimeLoop(this.soundTemplate, timeMilliSecond, delay / this.rhythmFigure.getRhythmFigureValue());
        timer.setBeatsMax(this.beatsMax);
        timer.setRhythmFigure(this.rhythmFigure);
        timer.start();
        Looper.loop();
    }

    /**
     * Executado ao encerrar o service
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stopMetronomo();
    }

    /**
     * Para o metronomo e encerra a Thread
     */
    public void stopMetronomo() {
        timer.cancel();
        stopSelf();
    }

    /**
     * Converte de BPM para BPS
     *
     * @param bpm - Frequencia definida pelo usuário.
     * @return Frequencia convertida para batidas por segundo.
     */
    private double conversorBPM(long bpm) {
        return bpm / (double) 60;
    }

    /**
     * Define a figura rítmica. Caso esteja fora do intervalo é definido um valor padrão.
     *
     * @param rhythmFigure - Figura rítmica definido pelo usuário.
     * @param timeInMinute - Tempo de duração do metronomo
     * @param frequencyBpm - frequencia em bpm
     * @param beats        - quantidade de beats máximo
     */
    public void setSoundConfiguration(RhythmFigure rhythmFigure, int timeInMinute, long frequencyBpm, int beats) {
        // Figura rítmica
        this.rhythmFigure = rhythmFigure;
        // Tempo em minutos
        int timePlayMin = InputLimit.TIME_PLAY_MIN.getInputLimitValue();
        int timePlayMax = InputLimit.TIME_PLAY_MAX.getInputLimitValue();

        if ((timeInMinute < timePlayMin) || (timeInMinute > timePlayMax)) {
            //Valor padrão caso esteja fora dos limites.
            this.timeInMinute = timePlayMin;

        } else {

            this.timeInMinute = timeInMinute;
        }
        // Frequencia em BPM
        int frequencyMin = InputLimit.FREQUENCY_MIN.getInputLimitValue();
        int frequencyMax = InputLimit.FREQUENCY_MAX.getInputLimitValue();
        int frequencyDefault = InputLimit.FREQUENCY_DEFAULT.getInputLimitValue();

        if ((frequencyBpm < frequencyMin) || (frequencyBpm > frequencyMax)) {
            //Valor padrão caso esteja fora dos limites.
            this.frequencyBpm = frequencyDefault;

        } else {

            this.frequencyBpm = frequencyBpm;
        }
        // Batidas
        int beatMin = InputLimit.BEATS_MIN.getInputLimitValue();
        int beatMax = InputLimit.BEATS_MAX.getInputLimitValue();
        int beatDefault = InputLimit.BEATS_DEFAULT.getInputLimitValue();

        if ((beats < beatMin) || (beats > beatMax)) {
            //Valor padrão caso esteja fora dos limites.
            this.beatsMax = beatDefault;

        } else {

            this.beatsMax = beats;
        }
    }
}
