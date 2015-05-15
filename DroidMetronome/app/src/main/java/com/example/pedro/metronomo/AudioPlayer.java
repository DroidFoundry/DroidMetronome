package com.example.pedro.metronomo;

import android.media.SoundPool;

/**
 * Created by pedro on 12/05/15.
 */
public class AudioPlayer implements Runnable{
    private int somAlto;
    private int somBaixo;

    private SoundPool sound;

    private int batidasMaximo;
    private int batidasAtual;

    public AudioPlayer(SoundPool sound,int somAlto,int somBaixo){
        this.sound = sound;
        this.somAlto = somAlto;
        this.somBaixo = somBaixo;

        this.batidasAtual = 0;
    }

    public void setBatidasMaximo(int batidasMaximo) {
        this.batidasMaximo = batidasMaximo;
    }

    public int getBatidasMaximo() {
        return batidasMaximo;
    }

    @Override
    public void run() {
        //Log.d("TIME",String.valueOf(batidasAtual) + "/" + String.valueOf(batidasMaximo));
        if(this.batidasAtual < this.batidasMaximo) {
            this.sound.play(somAlto,1,1,1,0,1);
            this.batidasAtual++;

        }else{
            this.sound.play(somBaixo,1,1,1,0,1);
            this.batidasAtual = 1;

        }
    }
}
