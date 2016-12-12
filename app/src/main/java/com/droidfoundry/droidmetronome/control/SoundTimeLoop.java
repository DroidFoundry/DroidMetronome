package com.droidfoundry.droidmetronome.control;

import android.os.CountDownTimer;
import android.os.Looper;
import com.droidfoundry.droidmetronome.model.RhythmFigure;
import com.droidfoundry.droidmetronome.model.SoundTemplate;

/**
 * Created by pedro on 12/05/15.
 */
public class SoundTimeLoop extends CountDownTimer {

    private SoundTemplate templateSound;
    private RhythmFigure rhythmFigure;
    private boolean starting = false;
    private int beatsMax;
    private int beatsCurrent = 0;

    /**
     * Construtor do timer
     *
     * @param soundTemplate     - som a ser tocado
     * @param millisInFuture    - tempo máximo
     * @param countDownInterval - tempo entre toques
     */
    public SoundTimeLoop(SoundTemplate soundTemplate, long millisInFuture, long countDownInterval) {

        super(millisInFuture, countDownInterval);
        // Tempo de intervalo
        HardwareActions.getInstance().setDelay(countDownInterval);
        // Som a ser tocado
        this.templateSound = soundTemplate;
    }

    /**
     * Método para cada tick
     *
     * @param l
     */
    @Override
    public void onTick(long l) {

        int rhythmFigureValue = rhythmFigure.getRhythmFigureValue();
        int rhythmFigureDecrement = rhythmFigureValue--;

        try {
            // Ativando luz
            HardwareActions.getInstance().activeLighting();

            if ((rhythmFigureValue > 1) && (!this.starting)) {

                int rhythmFigureTemp = rhythmFigureValue - rhythmFigureDecrement;
                beatsMax = beatsMax * rhythmFigureTemp;
                this.starting = true;
            }

            if (beatsCurrent < beatsMax) {
                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                templateSound.playSoundAlto();
                beatsCurrent++;

            } else {
                // Modo vibratório
                HardwareActions.getInstance().activeVibration();
                templateSound.playSoundBaixo();
                beatsCurrent = 1;
            }
            // Desativar flash
            HardwareActions.getInstance().desactiveLighting();

        } catch (RuntimeException e) {

            throw e;

        } finally {
            // Desativar flash
            HardwareActions.getInstance().desactiveLighting();
        }
    }

    /**
     * Método executado ao encerrar o timer
     */
    @Override
    public void onFinish() {

        Looper.myLooper().quit();
    }

    /**
     * Método que define uma figura ritmica para o contador
     *
     * @param rhythmFigure
     */
    public void setRhythmFigure(RhythmFigure rhythmFigure) {

        this.rhythmFigure = rhythmFigure;
    }

    /**
     * Método para adicionar as batidas máximo
     *
     * @param beatsMax
     */
    public void setBeatsMax(int beatsMax) {

        this.beatsMax = beatsMax;
    }
}
