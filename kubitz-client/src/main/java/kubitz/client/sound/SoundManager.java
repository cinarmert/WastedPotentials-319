package kubitz.client.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

    private final String BACKGROUND_MUSIC_1 = "/sounds/music/m1.wav";

    private Clip backgroundMusic1;

    public SoundManager(){
        getSoundFiles();
    }

    private void getSoundFiles(){

        backgroundMusic1 = setMusic(BACKGROUND_MUSIC_1);
        backgroundMusic1.loop(Clip.LOOP_CONTINUOUSLY);

    }

    private Clip setMusic(String path){
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource(BACKGROUND_MUSIC_1));
            clip.open(inputStream);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return clip;
    }

    public void startBackgroundMusic(){
        backgroundMusic1.start();
    }

    public void stopBackgroundMusic(){
        backgroundMusic1.stop();
    }

}
