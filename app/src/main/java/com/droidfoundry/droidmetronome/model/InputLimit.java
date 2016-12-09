package com.droidfoundry.droidmetronome.model;

/**
 * Created by JuliannyAS on 09/12/2016.
 */

public enum InputLimit {

    FREQUENCY_MIN(1),
    FREQUENCY_MAX(300),
    FREQUENCY_DEFAULT(120),
    TIME_PLAY_MIN(1),
    TIME_PLAY_MAX(15),
    BEATS_MIN(1),
    BEATS_MAX(16),
    BEATS_DEFAULT(4),
    ;


    private final int inputLimitValue;

    /**
     * Construtor de figura ritmica
     * @param inputLimitValue o rhythmFigureValue da figura ritmica
     */
    InputLimit(int inputLimitValue){

        this.inputLimitValue = inputLimitValue;
    }

    /**
     * retorna o rhythmFigureValue da figura ritmica
     * @return figura ritmica
     */
    public int getInputLimitValue(){

        return inputLimitValue;
    }
}
