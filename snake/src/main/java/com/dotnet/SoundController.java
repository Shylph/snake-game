package com.dotnet;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundController {

    private AudioInputStream ais;
    private Clip background1;

    public SoundController() {
    }

    public void play_background() {
        try {
            background1 = AudioSystem.getClip();
            background1.open( AudioSystem.getAudioInputStream(new File("res/bgm_nature.wav")));
            background1.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void stop_background() {
        background1.stop();
        background1.close();
    }
}
