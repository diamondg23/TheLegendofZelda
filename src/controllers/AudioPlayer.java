package controllers;
import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    private Clip clip;

    public void playOverWorldLoop(String filePath) {
        try {
        	AudioInputStream audioStream = AudioSystem.getAudioInputStream(
        		    getClass().getResource(filePath)
        		);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}