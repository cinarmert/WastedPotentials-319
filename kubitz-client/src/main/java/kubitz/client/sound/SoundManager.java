package kubitz.client.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {

    private static final String BACKGROUND_MUSIC_1 = "/sounds/music/m1.wav";

    private static Clip backgroundMusic1;

    public SoundManager(){
        getSoundFiles();
    }

    private void getSoundFiles(){

        SoundManager.backgroundMusic1 = setMusic(BACKGROUND_MUSIC_1);

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

    public static void startBackgroundMusic(){
        SoundManager.backgroundMusic1.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopBackgroundMusic(){
        SoundManager.backgroundMusic1.stop();
    }

}
