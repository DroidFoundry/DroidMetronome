package com.droidfoundry.droidmetronome.control;

/**
 * Responsavel por reportar as exceções de campos vazios na interface
 */
class FieldEmptyException extends IllegalArgumentException {

    /**
     * Contrutor da classe de exceção
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
