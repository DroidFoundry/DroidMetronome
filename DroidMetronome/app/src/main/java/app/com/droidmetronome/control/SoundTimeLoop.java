package app.com.droidmetronome.control;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Vibrator;

import app.com.droidmetronome.model.FiguraRitmica;
import app.com.droidmetronome.model.FrontConversor;
import app.com.droidmetronome.model.TemplateSound;

/**
 * Created by pedro on 12/05/15.
 */
public class SoundTimeLoop extends CountDownTimer{

    private TemplateSound som;
    private FiguraRitmica figuraRitmica;
    private Context context;

    private Vibrator vibrate;
    private Camera camera;

    private boolean isVibrating;
    private boolean isLighting;
    private boolean hardwareSupported;
    private boolean starting = false;

    private int batidasMaximo;
    private int batidasAtual = 0;

    private long delay;

    /**
     * Construtor do timer
     * @param millisInFuture - tempo máximo
     * @param countDownInterval - tempo entre toques
     */
    public SoundTimeLoop(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        // Tempo de intervalo
        this.delay = countDownInterval;

        // Som a ser tocado
        TemplateSound som = FrontConversor.getInstance().getSound();
        this.som = som;

    }

    /**
     * Método para cada tick
     * @param l
     */
    @Override
    public void onTick(long l){
        try {
            // Ativando luz
            activeLighting(isLighting);


            if((figuraRitmica.getValue() >1 ) && (!this.starting)){
                batidasMaximo = batidasMaximo * this.figuraRitmica.getValue() - (this.figuraRitmica.getValue() - 1);
                this.starting = true;
            }

            if(batidasAtual < batidasMaximo) {

                // Modo vibratório
                activeVibration(isVibrating, (int) Math.ceil(delay));
                som.playSoundAlto();

                batidasAtual++;
            }else{

                // Modo vibratório
                activeVibration(isVibrating, (int) Math.ceil(delay));
                som.playSoundBaixo();

                batidasAtual = 1;
            }

            // Desativar flash
            desactiveLighting(isLighting);


        }catch(Exception erro) {
            erro.printStackTrace();
        } finally {
            // Desativar flash
            desactiveLighting(isLighting);
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
     * Iniciando configuração de sistema do metronomo
     */
    public void startConfiguration(){
        // Configurações gerais
        this.vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        this.hardwareSupported = HardwareSupported();
    }

    /**
     * Método que define uma figura ritmica para o contador
     * @param figuraRitmica
     */
    public void setFiguraRitmica(FiguraRitmica figuraRitmica){
        this.figuraRitmica = figuraRitmica;
    }

    /**
     * Método para adicionar um contexto.
     * @param context
     */
    public void setContext(Context context){
        this.context = context;
    }

    /**
     * Método para adicionar as batidas máximo
     * @param batidasMaximo
     */
    public void setBatidasMaximo(int batidasMaximo){
        this.batidasMaximo = batidasMaximo;
    }

    /**
     * Define as configurações de hardware
     * @param vibrating - Define vibrações
     * @param lighting - Define luz
     */
    public void setHardwareConfiguration(boolean vibrating ,boolean lighting){
        this.isVibrating = vibrating;
        this.isLighting = lighting;

        this.hardwareSupported = this.HardwareSupported();
    }

    /**
     * Verifica se o hardware é compativel
     * @return
     */
    private boolean HardwareSupported(){
        PackageManager pm = context.getPackageManager();

        boolean flash = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean camera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        return(flash && camera);
    }

    /**
     * Ativando vibração
     * @param vibrating - flag que verifica se está disponivel
     * @param delay - duração
     */
    private void activeVibration(boolean vibrating ,long delay){
        if(vibrating)
            vibrate.vibrate(delay/10);
    }

    /**
     * Ativa a luz
     * @param lighting - flag que verifica se está disponivel
     */
    private void activeLighting(boolean lighting){
        if((lighting) && (hardwareSupported)){

            if(camera == null) {

                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        }
    }

    /**
     * Desativa a luz
     * @param lighting - flag que verifica se está disponivel
     */
    private void desactiveLighting(boolean lighting){
        if((lighting) && (hardwareSupported)){

            if(camera != null) {
                camera.release();
                camera = null;
            }
        }
    }
}