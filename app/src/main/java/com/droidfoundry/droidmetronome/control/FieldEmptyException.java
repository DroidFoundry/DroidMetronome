package com.droidfoundry.droidmetronome.control;

/**
 * Responsavel por reportar as exceções de campos vazios na interface
 */
public class FieldEmptyException extends IllegalArgumentException {
    /**
     * cria instancia para lançamento da exceção
     */
    public FieldEmptyException() {

    }

    /**
     * reporta a view a exceção de campo vazio
     * @param msg
     */
    public FieldEmptyException(String msg) {

        super(msg);
    }
}
