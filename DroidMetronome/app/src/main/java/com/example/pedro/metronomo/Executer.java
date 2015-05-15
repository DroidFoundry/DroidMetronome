package com.example.pedro.metronomo;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.Toast;

/**
 * Created by pedro on 12/05/15.
 */
public class Executer {

    private AudioPlayer som;
    private Compasso compasso;

    public void preExecuter(Context context){
        SoundPool sound = new SoundPool(4, AudioManager.STREAM_MUSIC,0);

        int id_Alto = sound.load(context,R.raw.beep_2,1);
        //Log.d("TIME", "Alto: " + String.valueOf(id_Alto));
        int id_Baixo = sound.load(context,R.raw.beep_1,1);
        //Log.d("TIME","Baixo: "+String.valueOf(id_Baixo));


        this.compasso = new Compasso();
        this.som = new AudioPlayer(sound,id_Alto,id_Baixo);

        //Definindo configurações do metronomo
        this.compasso.setFrequenciaBPM(120); // Frequencia em BPM
        this.compasso.setTempoMinutos(1); // Duração em minutos
        this.compasso.setSom(this.som); // Som a ser tocado (Alto e baixo)
        this.compasso.setQuantidadeBatidas(4); // Quantidade de batidas por ciclo
        //====================================

    }

    public void onExecuter(Context context){

        try {
            this.compasso.startMetronomo();

        } catch (InterruptedException e) {
            Toast mensagem = Toast.makeText(context, "Ocorreu um erro.", Toast.LENGTH_SHORT);
            mensagem.show();

        }

        Toast mensagem = Toast.makeText(context, "Compasso já inicializado.", Toast.LENGTH_SHORT);
        mensagem.show();

    }
}
