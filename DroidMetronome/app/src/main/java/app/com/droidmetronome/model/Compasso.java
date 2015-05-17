package app.com.droidmetronome.model;



/**
 * Created by pedro on 12/05/15.
 */
public class Compasso extends Thread {
    private long frequenciaBPM;
    private boolean stopNow;

    private AudioPlayer som;

    /**
     * Define a frequencia da batida em BPM
     * @param frequenciaBPM
     */
    public void setFrequenciaBPM(long frequenciaBPM) {
        this.frequenciaBPM = frequenciaBPM;
    }

    /**
     * Define os sons a serem tocados
     * @param som
     */
    public void setSom(AudioPlayer som) { this.som = som; }

    /**
     * Define a quantidade de batidas tocadas por ciclo
     * @param batidas
     */
    public void setQuantidadeBatidas(int batidas){
        this.som.setBatidasMaximo(batidas);
    }

    /**
     * Converte de BPM para BPS
     * @param bpm
     * @return
     */
    private double conversorBPM(long bpm){return (bpm/(double)60);}

    /**
     * Execução da o Metronomo em uma thread separada
     */
    @Override
    public void run() {
        double frequenciaSegundos = this.conversorBPM(this.frequenciaBPM);
        double Delay = 1000/frequenciaSegundos;

        this.stopNow = false;

        try {
            while(true) {

                som.play();
                Thread.sleep((long) Delay);

                if(stopNow) break;
            }
        } catch (InterruptedException e) {

        } finally {
            som.stop();
        }

    }

    /**
     * Para o metronomo e encerra a Thread
     */
    public void stopMetronomo(){
        this.stopNow = true;
        som.stop();
    }

}