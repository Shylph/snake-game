package com.dotnet;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundController {

    private Clip background;


    public void playBackground(int stage) {
        String pathname = "res/sounds/1번 배경.wav";
        if (stage == 1) {
            pathname = "res/sounds/2번 배경.wav";
        } else if (stage == 2) {
            pathname = "res/sounds/3번 배경.wav";
        }
        if (background != null && background.isRunning()) {
            background.stop();
            background.close();
        }
        try {
            background = AudioSystem.getClip();
            background.open(AudioSystem.getAudioInputStream(new File(pathname)));
            background.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playGameOver() {
        String pathname = "res/sounds/game over.wav";
        playPath(pathname);
    }

    private void playPath(String pathname) {
        try {
            Clip gameOver = AudioSystem.getClip();
            gameOver.open(AudioSystem.getAudioInputStream(new File(pathname)));
            gameOver.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playNextStage() {
        String pathname = "res/sounds/스테이지 넘어가는 소리.wav";
        playPath(pathname);
    }

    public void stop_background() {
        background.stop();
        background.close();
    }

}
