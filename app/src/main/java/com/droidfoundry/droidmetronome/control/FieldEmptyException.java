package com.droidfoundry.droidmetronome.control;

class FieldEmptyException extends IllegalArgumentException {

    public FieldEmptyException() {

    }


    public FieldEmptyException(String msg) {

        super(msg);
    }
}
