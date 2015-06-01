package app.com.droidmetronome.model;

/**
 * Created by pedro on 12/05/15.
 */
public class Compasso extends Thread {
    private long frequenciaBPM;
    private int tempoMinutos;
    private int figuraRitmica;
    private boolean stopNow;

    private AudioPlayer som;

    /**
     * Define a figura rítmica. Caso esteja fora do intervalo é definido um valor padrão.
     * @param figuraRitmica - Figura rítmica definido pelo usuário.
     */
    public void setFiguraRitmica(int figuraRitmica){
        if((figuraRitmica < 1)||(figuraRitmica > 32)){
            //Valor padrão caso esteja fora dos limites.
            this.figuraRitmica = 1;
        }else {
            this.figuraRitmica = figuraRitmica;
        }
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
     * @see AudioPlayer
     */
    public void setSom(AudioPlayer som) {
        this.som = som;
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     * @param batidas - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setQuantidadeBatidas(int batidas) {
        if((batidas < 1)||(batidas > 16)){
            //Valor padrão caso esteja fora dos limites.
            this.som.setBatidasMaximo(4);
        }else {
            this.som.setBatidasMaximo(batidas);
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
        double frequenciaSegundos = this.conversorBPM(this.frequenciaBPM);
        double Delay = 1000 / frequenciaSegundos;

        int tempoMiliSegundos = (this.tempoMinutos * 60 * 1000); // (60000 milisegundos)
        int quantidadeCiclo = (int) Math.floor(tempoMiliSegundos / (som.getBatidasMaximo() * Delay));

        this.stopNow = false;

        try {
            int contador = 0;
            boolean inLoop = false;

            // Iniciando ciclo de batidas
            while (!stopNow) {

                for(int i=1; i <= figuraRitmica ;i++) {

                    if( i == figuraRitmica){inLoop = false;}
                    som.play(inLoop);
                    Thread.sleep((long)Delay/figuraRitmica);
                }
                inLoop = true;
                contador++;

                if(contador >= som.getBatidasMaximo()*quantidadeCiclo) break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.stopMetronomo();
        }

    }

    /**
     * Para o metronomo e encerra a Thread
     * @see AudioPlayer
     */
    public void stopMetronomo() {
        this.stopNow = true;
        som.stop();
    }
}