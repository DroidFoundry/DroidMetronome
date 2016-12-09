package com.droidfoundry.droidmetronome.control;


import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

import com.droidfoundry.droidmetronome.R;

/**
 * Created by paulo on 09/12/16.
 */

public class ClickListenerModel {

    private TextView descricaoSeekbar;
    private int max;
    private int min;
    private Context context;

    /**
     * Constroi u
     * @param descricaoSeekbar
     * @param min
     * @param max
     * @param context
     */
    public ClickListenerModel(TextView descricaoSeekbar, int min, int max, Context context) {
        this.descricaoSeekbar = descricaoSeekbar;
        this.min = min;
        this.max = max;
        this.context = context;

    }


    SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub

            if (progress < min) {
                seekBar.setProgress(min);

            } else if (progress > max) {
                seekBar.setProgress(max);
            } else {
                descricaoSeekbar.setText(Integer.toString(progress));

            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            int corTextoSeekBarAtivada = context.getResources().getColor(R.color.color_accent);
            descricaoSeekbar.setTextColor(corTextoSeekBarAtivada);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int corNormalTexto = context.getResources().getColor(R.color.color_primary_text);
            descricaoSeekbar.setTextColor(corNormalTexto);
        }
    };

    public SeekBar.OnSeekBarChangeListener getSeekBarListener() {
        return seekBarListener;
    }
}
