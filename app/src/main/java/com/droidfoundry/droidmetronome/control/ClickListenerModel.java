package com.droidfoundry.droidmetronome.control;

import android.content.Context;
import android.widget.SeekBar;
import android.widget.TextView;

import com.droidfoundry.droidmetronome.R;

/**
 * Created by paulo on 09/12/16.
 */

public class ClickListenerModel {

    private TextView barHistory;
    private int max;
    private int min;
    private Context context;

    /**
     * Cria som para ouvir o metronomo
     *
     * @param barHistory
     * @param min
     * @param max
     * @param context
     */
    public ClickListenerModel(TextView barHistory, int min, int max, Context context) {
        this.barHistory = barHistory;
        this.min = min;
        this.max = max;
        this.context = context;

    }

   SeekBar.OnSeekBarChangeListener listenerDroid
           = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress < min) {
                seekBar.setProgress(min);
            } else if (progress > max) {
                seekBar.setProgress(max);
            } else {
                barHistory.setText(Integer.toString(progress));
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            int barOnTextColor = context.getResources().getColor(R.color.color_accent);
            barHistory.setTextColor(barOnTextColor);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int barOffTextColor = context.getResources().getColor(R.color.color_primary_text);
            barHistory.setTextColor(barOffTextColor);
        }
    };

    SeekBar.OnSeekBarChangeListener getListenerDroid() {
        return listenerDroid;
    }
}
