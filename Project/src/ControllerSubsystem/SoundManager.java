package ControllerSubsystem;
import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by berkyurttas on 26/04/16.
 */
public class SoundManager {
    private File background, dead, bomb;
    private AudioInputStream stream;
    private Clip bClip, sClip;
    private boolean playMusic, playSound, isPlaying;

    public SoundManager(){
        background = new File("src/Sources/Sounds/background.wav");
        dead = new File("src/Sources/Sounds/dead.wav");
        bomb = new File("src/Sources/Sounds/bomb.wav");
        isPlaying = false;
    }
    public void playMusic() {
        if(playMusic && !isPlaying) {
            try {
                stream = AudioSystem.getAudioInputStream(background.getAbsoluteFile());
                bClip = AudioSystem.getClip();
                bClip.open(stream);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (bClip != null) {
                    new Thread() {
                        public void run() {
                            synchronized (bClip) {
                                bClip.stop();
                                bClip.setFramePosition(0);
                                isPlaying = true;
                                bClip.loop(Clip.LOOP_CONTINUOUSLY);
                            }
                        }
                    }.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void stopMusic() {
       if(playMusic) {
           isPlaying = false;
           bClip.stop();
       }
    }
    public void playSound(int type) {
        if(playSound) {
            File current = null;
            if (type == 0)
                current = dead;
            else if (type == 1)
                current = bomb;
            if (current != null) {
                try {
                    stream = AudioSystem.getAudioInputStream(current.getAbsoluteFile());
                    sClip = AudioSystem.getClip();
                    sClip.open(stream);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (sClip != null) {
                        new Thread() {
                            public void run() {
                                synchronized (sClip) {
                                    sClip.stop();
                                    sClip.setFramePosition(0);
                                    sClip.start();
                                }
                            }
                        }.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setMusic(boolean flag){
        this.playMusic = flag;
    }
    public void setSound(boolean flag){
        this.playSound = flag;
    }
    public boolean isMusicOn(){
        return playMusic;
    }
    public boolean isSoundOn(){
        return playSound;
    }
}
