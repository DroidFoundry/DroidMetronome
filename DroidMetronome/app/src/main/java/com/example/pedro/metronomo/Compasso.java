package com.example.pedro.metronomo;

import android.util.Log;

/**
 * Created by pedro on 12/05/15.
 */
public class Compasso extends Thread{
    private long frequenciaBPM;
    private int tempoMinutos;

    private NotaAudio som;

    public void setTempoMinutos(int tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public void setFrequenciaBPM(long frequenciaBPM) {
        this.frequenciaBPM = frequenciaBPM;
    }

    public void setSom(NotaAudio som) { this.som = som; }

    public void setQuantidadeBatidas(int batidas){
        this.som.setBatidasMaximo(batidas);
    }

    public void startMetronomo() throws InterruptedException {
        //Handler handler = new Handler();
        //Para 30 BPM 4/4

        double frequenciaSegundos = this.frequenciaBPM/(double)60; // (0,5 Batidas/segundo)
        double Delay = 1000/frequenciaSegundos; // (2000 milisegundos)
        int tempoMiliSegundos = (this.tempoMinutos * 60 * 1000); // (60000 milisegundos)

        int quantidadeCiclo = (int)Math.floor(tempoMiliSegundos/(som.getBatidasMaximo() * Delay)); // 60/8 (8 ciclos)

        //som.run();
        for(int contador = 0; contador < quantidadeCiclo; contador++){

            for(int index = 0;index < som.getBatidasMaximo();index++) {
                Log.d("TIME", "bip " + (index + 1));

                som.run();
                Thread.sleep((long)Delay);

            }
            Log.d("TIME","Fim do ciclo: " + String.valueOf(contador+1));
            Log.d("TIME","Delay: " + String.valueOf(Delay));
        }

    }
}
