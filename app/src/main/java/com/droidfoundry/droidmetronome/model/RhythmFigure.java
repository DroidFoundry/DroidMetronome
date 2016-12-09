package com.droidfoundry.droidmetronome.model;

/**
 * Define as figuras ritmicas do sistema
 */
public enum RhythmFigure {

    WHOLE_NOTE(1),
    HALF_NOTE(2),
    QUARTER_NOTE(3),
    EIGHTH_NOTE(4),
    SIXTEENTH_NOTE(5),
    THIRTY_SECOND_NOTE(6),
    SIXTY_FOURTH_NOTE(7);


    private final int rhythmFigureValue;

    /**
     * Construtor de figura ritmica
     * @param rhythmFigureValue o rhythmFigureValue da figura ritmica
     */
    RhythmFigure(int rhythmFigureValue){

        this.rhythmFigureValue = rhythmFigureValue;
    }

    /**
     * retorna o rhythmFigureValue da figura ritmica
     * @return figura ritmica
     */
    public int getRhythmFigureValue(){

        return rhythmFigureValue;
    }
}
