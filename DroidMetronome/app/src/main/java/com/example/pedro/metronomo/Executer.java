package com.example.pedro.metronomo;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by pedro on 12/05/15.
 */
public class Executer {

    private Compasso compasso;

    /**
     * Pre-execução do metronomo , definindo sons e valores.
     * @param context
     */
    public void preExecuter(Context context){

        SoundPool sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        int id_Alto = sound.load(context,R.raw.beep_2,1);
        int id_Baixo = sound.load(context,R.raw.beep_1,1);

        this.compasso = new Compasso();
        AudioPlayer som = new AudioPlayer(sound,id_Alto,id_Baixo);

        //Definindo configurações do metronomo
        this.compasso.setFrequenciaBPM(120); // Frequencia em BPM
        this.compasso.setSom(som); // Som a ser tocado (Alto e baixo)
        this.compasso.setQuantidadeBatidas(4); // Quantidade de batidas por ciclo
        //====================================

    }

    /**
     * Define a prioridade da Thread e executa o metronomo
     */
    public void onExecuter(){

        compasso.setPriority(Thread.MIN_PRIORITY);
        compasso.start();
    }

    /**
     * Encerra o metronomo
     */
    public void stopExecuter(){
        if(this.compasso != null){
            this.compasso.stopMetronomo();
            this.compasso = null;
        }
    }
}