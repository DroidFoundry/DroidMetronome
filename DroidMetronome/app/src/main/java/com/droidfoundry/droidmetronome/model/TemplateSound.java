package com.droidfoundry.droidmetronome.model;

/**
 * Created by pedro on 03/06/15.
 */
public interface TemplateSound {
    void playSoundAlto();
    void playSoundBaixo();
    TemplateSound getSound();
    void stopSound();
}
