package com.droidfoundry.droidmetronome.control;

public class FieldEmptyException extends IllegalArgumentException {

    public FieldEmptyException() {

    }


    public FieldEmptyException(String msg) {

        super(msg);
    }
}
