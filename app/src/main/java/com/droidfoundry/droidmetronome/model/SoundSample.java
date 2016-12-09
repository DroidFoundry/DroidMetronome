package com.droidfoundry.droidmetronome.model;

import com.droidfoundry.droidmetronome.R;

public enum SoundSample {

    BITS_HIGH(R.raw.bit8_02),
    BITS_LOW(R.raw.bit8_01),
    BEEP_HIGH(R.raw.beep_2),
    BEEP_LOW(R.raw.beep_1),
    HIHATS_HIGH(R.raw.hihats_02),
    HIHATS_LOW(R.raw.hihats_01),
    KICKCLAP_HIGH(R.raw.kick_clap_02),
    KICKCLAP_LOW(R.raw.kick_clap_01),
    RIMSHOT_HIGH(R.raw.rim_shot_01),
    RIMSHOT_LOW(R.raw.rim_shot_02);

    private final int soundSampleValue;

    /**
     * Construtor de SoundSample
     *
     * @param soundSampleValue o identificador do som
     */
    SoundSample(int soundSampleValue){

        this.soundSampleValue = soundSampleValue;
    }

    /**
     * Retorna o identificador do som
     *
     * @return o identificador do som
     */
    public int getSoundSampleValue(){

        return soundSampleValue;
    }
}
