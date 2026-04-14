package controllers;
import javax.sound.sampled.*;
import java.io.File;

public class AudioPlayer {

    private Clip clip;

    public void playMusicLoop(String filePath) { // to change what song you are playing just pass a diff file path
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