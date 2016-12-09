package com.droidfoundry.droidmetronome.model;

import android.content.Context;

/**
 * Intermediario das ações do usario para o sistema
 */
public class UserInterface {

    private SoundTemplate sound;
    private RhythmFigure rhythmFigure;
    private long frequencyBPM;
    private int beatsQuantity;
    private int timeInMinutes;
    private boolean vibration;
    private boolean flash;

    /**
     * Define o som do metrônomo com base no definido pelo usuário.
     *
     * @param typeSoundValue - Id para escolha do som (caso não exista é atribuido um valor padrão).
     * @param context        - Contexto aonde o som será exibido.
     */
    public void createSomById(int typeSoundValue, Context context) {

        switch (typeSoundValue) {
            //Som 1:
            case 1:
                sound = new SoundTemplate(context, SoundSample.BITS_HIGH, SoundSample.BITS_LOW);
                break;

            //Som 2:
            case 2:
                sound = new SoundTemplate(context, SoundSample.HIHATS_HIGH, SoundSample.HIHATS_LOW);
                break;

            //Som 3:
            case 3:
                sound = new SoundTemplate(context, SoundSample.KICKCLAP_HIGH, SoundSample.KICKCLAP_LOW);
                break;

            //Som 4:
            case 4:
                sound = new SoundTemplate(context, SoundSample.BEEP_HIGH, SoundSample.BEEP_LOW);
                break;

            //Som 5:
            case 5:
                sound = new SoundTemplate(context, SoundSample.RIMSHOT_HIGH, SoundSample.RIMSHOT_LOW);
                break;

            //Som padrão
            default:
                sound = new SoundTemplate(context, SoundSample.BITS_HIGH, SoundSample.BITS_LOW);
        }
    }

    /**
     * Define a figura rítmica definido pelo usuário.
     *
     * @param rhythmFigureValue - Id para escolha da figura ritmica (caso não exista é atribuido um valor padrão).
     */
    public void createRhythmFigureByValue(int rhythmFigureValue) {

        switch (rhythmFigureValue) {

            case 1:
                rhythmFigure = RhythmFigure.WHOLE_NOTE;
                break;

            case 2:
                rhythmFigure = RhythmFigure.HALF_NOTE;
                break;

            case 3:
                rhythmFigure = RhythmFigure.QUARTER_NOTE;
                break;

            case 4:
                rhythmFigure = RhythmFigure.EIGHTH_NOTE;
                break;

            case 5:
                rhythmFigure = RhythmFigure.SIXTEENTH_NOTE;
                break;

            case 6:
                rhythmFigure = RhythmFigure.THIRTY_SECOND_NOTE;
                break;

            case 7:
                rhythmFigure = RhythmFigure.SIXTY_FOURTH_NOTE;
                break;

            //Som padrão
            default:
                rhythmFigure = RhythmFigure.WHOLE_NOTE;

        }
    }

    /**
     * Permição de vibrar durante a execução
     *
     * @param vibration - Ativado se verdadeiro ,desativado se falso
     */
    public void setVibration(boolean vibration) {

        this.vibration = vibration;
    }

    /**
     * Permição de ativar o flash durante a execução
     *
     * @param flash - Ativado se verdadeiro ,desativado se falso
     */
    public void setFlash(boolean flash) {

        this.flash = flash;
    }

    /**
     * Define o tempo de duração em minutos. Caso esteja fora do intervalo é definido um valor padrão.
     *
     * @param timeInMinutes - Tempo em minutos definido pelo usuário.
     */
    public void setTimeInMinutes(int timeInMinutes) {

        if ((timeInMinutes < 1) || (timeInMinutes > 15)) {
            //Valor padrão caso esteja fora dos limites.
            this.timeInMinutes = 1;

        } else {

            this.timeInMinutes = timeInMinutes;
        }
    }

    /**
     * Define a frequencia em BPM's. Caso esteja fora do intervalo é definido um valor padrão.
     *
     * @param frequencyBPM - Frequencia em BPM definido pelo usuário.
     */
    public void setFrequencyBPM(long frequencyBPM) {

        if ((frequencyBPM < 10) || (frequencyBPM > 300)) {
            //Valor padrão caso esteja fora dos limites.
            this.frequencyBPM = 120;

        } else {

            this.frequencyBPM = frequencyBPM;
        }
    }

    /**
     * Define a quantidade máxima de batidas. Caso esteja fora do intervalo é definido um valor padrão.
     *
     * @param beatsQuantity - Quantidade máxima de batidas definido pelo usuário.
     */
    public void setBeatsQuantity(int beatsQuantity) {

        if ((beatsQuantity < 1) || (beatsQuantity > 16)) {
            //Valor padrão caso esteja fora dos limites.
            this.beatsQuantity = 4;

        } else {

            this.beatsQuantity = beatsQuantity;
        }
    }

    /**
     * Retorna o som selecionado
     *
     * @return
     */
    public SoundTemplate getSound() {

        return sound;
    }

    /**
     * Retorna a frequencia selecionada
     *
     * @return
     */
    public long getFrequencyBPM() {

        return frequencyBPM;
    }

    /**
     * Retorna a quantidade de batidas selecionada
     *
     * @return
     */
    public int getBeatsQuantity() {

        return beatsQuantity;
    }

    /**
     * retorna o tempo em minutos selecionado
     *
     * @return
     */
    public int getTimeInMinutes() {

        return timeInMinutes;
    }

    /**
     * Modo vibratorio ativo?
     *
     * @return
     */
    public boolean isVibration() {

        return vibration;
    }

    /**
     * Modo luminoso ativo?
     *
     * @return
     */
    public boolean isFlash() {

        return flash;
    }

    /**
     * Retorna a figura rítmica selecionada
     *
     * @return
     */
    public RhythmFigure getRhythmFigure() {

        return rhythmFigure;
    }
}
