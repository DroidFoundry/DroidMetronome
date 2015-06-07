package app.com.droidmetronome.model;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import app.com.droidmetronome.R;

/**
 * Created by pedro on 03/06/15.
 */
public class Sound_hiHats implements TemplateSound{

    private int somAlto;
    private int somBaixo;

    private SoundPool sound;
    private int idSom;

    private boolean readyAlto = false;

    public Sound_hiHats(Context context){
        this.sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        this.somAlto = sound.load(context, R.raw.hihats_02,1);
        sound.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){

            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                readyAlto = true;
            }
        });

        this.somBaixo = sound.load(context,R.raw.hihats_01,1);

    }

    @Override
    public void playSoundAlto() {
        if(readyAlto)
        idSom = this.sound.play(somAlto, 1, 1, 1, 0, 1);
    }

    @Override
    public void playSoundBaixo(){
        idSom = this.sound.play(somBaixo, 1, 1, 1, 0, 1);
    }

    @Override
    public TemplateSound getSound() {
        return this;
    }

    @Override
    public void stopSound() {
        this.sound.stop(this.idSom);
        this.sound.release();
    }
}
