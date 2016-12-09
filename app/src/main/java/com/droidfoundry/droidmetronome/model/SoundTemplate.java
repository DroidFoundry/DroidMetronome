package com.droidfoundry.droidmetronome.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundTemplate {

    private int highSound;
    private int lowSound;

    private SoundPool sound;
    private int soundValue;

    /**
     * Inicia o carregamento dos sons na memoria
     *
     * @param context   contexto da aplicacao
     * @param highSound identificador do som a ser executado
     * @param lowSound  identificador do som a ser executado
     */
    public SoundTemplate(Context context, SoundSample highSound, SoundSample lowSound) {

        this.sound = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        this.highSound = sound.load(context, highSound.getSoundSampleValue(), 1);
        this.lowSound = sound.load(context, lowSound.getSoundSampleValue(), 1);

        this.sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

                 @Override
                 public void onLoadComplete(SoundPool soundPool, int i, int i1) {

                 }
             }
        );
    }

    /**
     * Executa o som alto
     */
    public void playSoundAlto() {

        soundValue = this.sound.play(highSound, 1, 1, 1, 0, 1);
    }

    /**
     * Executa o som baixo
     */
    public void playSoundBaixo() {

        soundValue = this.sound.play(lowSound, 1, 1, 1, 0, 1);
    }

    /**
     * Para os sons
     */
    public void stopSound() {

        this.sound.stop(this.soundValue);
        this.sound.release();
    }
}

