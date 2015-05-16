package com.example.pedro.metronomo;

import android.media.SoundPool;

/**
 * Created by pedro on 12/05/15.
 */
public class AudioPlayer{
    private int somAlto;
    private int somBaixo;

    private SoundPool sound;

    private int batidasMaximo;
    private int batidasAtual;

    private int idSom;

    /**
     * Construtor
     * @param sound
     * @param somAlto
     * @param somBaixo
     */
    public AudioPlayer(SoundPool sound,int somAlto,int somBaixo){
        this.sound = sound;
        this.somAlto = somAlto;
        this.somBaixo = somBaixo;

        this.batidasAtual = 0;
    }

    /**
     * Define a quantidade máxima de batidas
     * @param batidasMaximo
     */
    public void setBatidasMaximo(int batidasMaximo) {
        this.batidasMaximo = batidasMaximo;
    }

    /**
     * Retorna a quantidade máxima de batidas
     * @return
     */
    public int getBatidasMaximo() {
        return batidasMaximo;
    }

    /**
     * Inicia os sons
     */
    public void play() {

        //Log.d("TIME",String.valueOf(batidasAtual) + "/" + String.valueOf(batidasMaximo));

        if(this.batidasAtual < this.batidasMaximo) {
            idSom = this.sound.play(somAlto,1,1,1,0,1);
            this.batidasAtual++;

        }else{
            idSom = this.sound.play(somBaixo,1,1,1,0,1);
            this.batidasAtual = 1;

        }
    }

    /**
     * Encerra os sons
     */
    public void stop(){
        this.sound.stop(this.idSom);
    }
}