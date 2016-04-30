package ControllerSubsystem;
import ModelSubsystem.Music;

import javax.sound.sampled.*;
import java.io.File;

//Saner Turhaner

public class SoundManager {
    //Music
    Music music;
    Music dead;
    Music bomb;

    //Constructor
    public SoundManager() {
        music = new Music("src/Sources/Sounds/music.wav");
        dead = new Music("src/Sources/Sounds/dead.wav");
        bomb = new Music("src/Sources/Sounds/bomb.wav");
    }

    //Necessary Method
    public void playGameMusic() {
        music.playMusic();
    }

    public void stopGameMusic() {
        music.stopMusic();
    }


    public void bombEffect() {
        bomb.playAsSound();
    }

    public void deadEffect() {
        dead.playAsSound();
    }
}