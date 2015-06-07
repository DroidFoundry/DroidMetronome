package app.com.droidmetronome.model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by pedro on 12/05/15.
 */
public class Compasso extends Thread{

    private long frequenciaBPM;
    private int tempoMinutos;

    private int batidasMaximo;

    private boolean stopNow;
    private boolean isVibrating;
    private boolean isLighting;

    private TemplateFiguraritmica figuraRitmica;
    private TemplateSound som;

    private Vibrator vibrate;
    private Camera camera;
    private Context context;

    /**
     * Construtor da classe compasso
     * @param context - Contexto ao qual o compasso será executado
     */
    public Compasso(Context context){
        this.context = context;
        this.vibrate = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    /**
     * Permitindo vibração
     * @param vibrating - Ativado se verdadeiro e desativado se falso
     */
    public void setVibrating(boolean vibrating){
        this.isVibrating = vibrating;
    }

    /**
     * Permitindo flash da camera
     * @param lighting - Ativado se verdadeiro e desativado se falso
     */
    public void setLighting(boolean lighting){
        this.isLighting = lighting;
    }

    /**
     * Define a figura rítmica. Caso esteja fora do intervalo é definido um valor padrão.
     * @param figuraRitmica - Figura rítmica definido pelo usuário.
     */
    public void setFiguraRitmica(TemplateFiguraritmica figuraRitmica){
        this.figuraRitmica = figuraRitmica;
    }

    /**
     * Define o tempo de duração em minutos. Caso esteja fora do intervalo é definido um valor padrão.
     * @param tempoMinutos - Tempo em minutos definido pelo usuário.
     */
    public void setTempoMinutos(int tempoMinutos) {
        if((tempoMinutos < 1)||(tempoMinutos > 15)){
            //Valor padrão caso esteja fora dos limites.
            this.tempoMinutos = 1;
        }else {
            this.tempoMinutos = tempoMinutos;
        }
    }

    /**
     * Define a frequencia em BPM's. Caso esteja fora do intervalo é definido um valor padrão.
     * @param frequenciaBPM - Frequencia em BPM definido pelo usuário.
     */
    public void setFrequenciaBPM(long frequenciaBPM) {
        if((frequenciaBPM < 10)||(frequenciaBPM > 300)){
            //Valor padrão caso esteja fora dos limites.
            this.frequenciaBPM = 120;
        }else {
            this.frequenciaBPM = frequenciaBPM;
        }
    }

    /**
     * Define os sons a serem tocados
     * @param som - O som a ser tocado.
     */
    public void setSom(TemplateSound som) {
        this.som = som;
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     * @param batidas - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setQuantidadeBatidas(int batidas) {
        if((batidas < 1)||(batidas > 16)){
            //Valor padrão caso esteja fora dos limites.
            this.batidasMaximo = 4;
        }else {
            this.batidasMaximo = batidas;
        }
    }

    /**
     * Converte de BPM para BPS
     *
     * @param bpm - Frequencia definida pelo usuário.
     * @return Frequencia convertida para batidas por segundo.
     */
    private double conversorBPM(long bpm) {
        return (bpm / (double) 60);
    }

    /**
     * Execução da o Metronomo em uma thread separada
     */
    @Override
    public void run() {
        double frequenciaSegundos = this.conversorBPM(this.frequenciaBPM); // 2bps
        double Delay = 1000 / frequenciaSegundos; // 0.5s

        int tempoMiliSegundos = (this.tempoMinutos * 60 * 1000); // (60000 milisegundos)
        double quantidadeCiclo = tempoMiliSegundos / (this.batidasMaximo * Delay);

        this.stopNow = false;

        try {

            // Iniciando ciclo de batidas
            loopSound(Delay, quantidadeCiclo);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.stopMetronomo();
        }

    }

    /**
     *
     * @param delay - Tempo entre as batidas (em milisegundos)
     * @param quantidadeCiclo - Quantidade de vezes que o loop é executado
     * @throws InterruptedException
     */

    private void loopSound(double delay , double quantidadeCiclo) throws InterruptedException{

        double contador = 0;
        int batidasAtual = 1;

        boolean inTimer;

        if(quantidadeCiclo == 0){
            inTimer = true;
        }else{
           inTimer = contador < this.batidasMaximo * quantidadeCiclo;
        }

        while ( (!stopNow) && (inTimer) ) {

            // Ativando luz
            try {
                activeLighting(isLighting);

                if(batidasAtual < batidasMaximo) {

                    for(int count = 1; count <= figuraRitmica.getValue(); count++){

                    // Modo vibratório
                    activeVibration(isVibrating, (int) Math.ceil(delay / figuraRitmica.getValue()));

                    som.playSoundAlto();

                    // Esperar
                    Thread.sleep((long) delay / figuraRitmica.getValue());

                    }
                    batidasAtual++;
                }else{

                        // Modo vibratório
                        activeVibration(isVibrating, (int) Math.ceil(delay / figuraRitmica.getValue()));

                        som.playSoundBaixo();

                        // Esperar
                        Thread.sleep((long) delay / figuraRitmica.getValue());

                    batidasAtual = 1;
                }

                // Desativar flash
                desactiveLighting(isLighting);

            }catch(RuntimeException erro){
                desactiveLighting(isLighting);
            }

            contador++;
        }
    }

    /**
     * Executa a vibração durante o compasso
     * @param vibrating - Verifica se a vibração está permitida
     * @param delay - duração da vibração
     */
    private void activeVibration(boolean vibrating ,long delay){
        if(vibrating)
            vibrate.vibrate(delay/10);
    }

    private boolean flashSuported(){
        PackageManager pm = context.getPackageManager();

        boolean flash = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        boolean camera = pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
        return(flash && camera);
    }

    private void activeLighting(boolean lighting){
        if((lighting) && (flashSuported())){

            if(camera == null) {

                camera = Camera.open();
                Camera.Parameters parameters = camera.getParameters();

                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameters);
                camera.startPreview();
            }
        }
    }

    private void desactiveLighting(boolean lighting){
        if((lighting) && (flashSuported())){

            if(camera != null) {
                camera.release();
                camera = null;
            }
        }
    }

    /**
     * Para o metronomo e encerra a Thread
     */
    public void stopMetronomo() {
        this.stopNow = true;
        desactiveLighting(isLighting);
        som.stopSound();
    }
}