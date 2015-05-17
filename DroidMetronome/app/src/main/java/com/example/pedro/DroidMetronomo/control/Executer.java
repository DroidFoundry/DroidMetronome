package com.example.pedro.DroidMetronomo.control;

import android.content.Context;

import com.example.pedro.DroidMetronomo.model.Compasso;

/**
 * Created by pedro on 12/05/15.
 */
public class Executer {

    private Compasso compasso;

    /**
     * Pre-execução do metronomo , definindo sons e valores.
     * @param context
     */
    public void preExecuter(Context context ,Compasso mainCompasso){

        //Definindo configurações do metronomo
        this.compasso = mainCompasso; // Som a ser tocado (Alto e baixo)
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