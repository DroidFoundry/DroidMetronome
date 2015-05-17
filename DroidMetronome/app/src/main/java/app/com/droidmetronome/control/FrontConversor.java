package app.com.droidmetronome.control;

/**
 * Created by pedro on 16/05/15.
 */
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.widget.NumberPicker;

import app.com.droidmetronome.model.AudioPlayer;
import app.com.droidmetronome.model.Compasso;
import app.com.droidmetronome.R;



/**
 * Created by pedro on 16/05/15.
 */
public class FrontConversor {
    private Compasso compasso = new Compasso();

    private AudioPlayer createSound(Context context){
        SoundPool sound = new SoundPool(10, AudioManager.STREAM_MUSIC,0);

        int id_Alto = sound.load(context, R.raw.beep_2,1);
        int id_Baixo = sound.load(context, R.raw.beep_1,1);

        AudioPlayer som = new AudioPlayer(sound,id_Alto,id_Baixo);

        return som;
    }

    public void setSom(Context context){
        compasso.setSom(this.createSound(context));
    }

    public void setFrequenciaBPM(NumberPicker frequenciaPicker){
        compasso.setFrequenciaBPM(frequenciaPicker.getValue());
    }

    public void setQuantidadeBatidas(NumberPicker frequenciaPicker){
        compasso.setQuantidadeBatidas(frequenciaPicker.getValue());
    }


    public Compasso toCompasso(){
        return compasso;
    }
}