package com.droidfoundry.droidmetronome.control;

/**
 * Created by paulo on 08/12/16.
 */

public class CampoVazioException extends IllegalArgumentException {
    public CampoVazioException(){

    }
    public CampoVazioException(String msg){
        super(msg);
    }
}
