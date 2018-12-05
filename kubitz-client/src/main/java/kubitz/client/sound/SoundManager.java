package kubitz.client.sound;

import kubitz.client.gui.Config;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;import javax.sound.sampled.FloatControl;

public class SoundManager {

    private static final String BACKGROUND_MUSIC_1 = "/sounds/music/m1.wav";
    private final String MENU_CLICK = "/sounds/sfx/menu_click.wav";
    private static Clip backgroundMusic1;
    private Clip menuClickSound;

    public SoundManager(){
        getSoundFiles();
    }

    private void getSoundFiles(){

        SoundManager.backgroundMusic1 = setMusic(BACKGROUND_MUSIC_1);
        menuClickSound = setMusic(MENU_CLICK);

    }

    private Clip setMusic(String path){
        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource(path));
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

    public void startMenuClickSound(){
        menuClickSound.setFramePosition(0);
        menuClickSound.start();

        //ToDo make this cleaner
        changeEffectsVolume((double) (Config.getMasterSound() * Config.getEffectsSound() / 100));

    }

    public void changeMusicVolume(double volume){
        double gain = (double)volume / 100;
        FloatControl gainControl = (FloatControl) backgroundMusic1.getControl(FloatControl.Type.MASTER_GAIN);
        float gainValue = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(gainValue);
    }

    public void changeEffectsVolume(double volume){
        double gain = (double)volume / 100;
        FloatControl gainControl = (FloatControl) menuClickSound.getControl(FloatControl.Type.MASTER_GAIN);
        float gainValue = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(gainValue);
    }

    public void initializeVolumes( double master, double effects, double music){
        changeMusicVolume( master * music / 100);
        changeEffectsVolume( master * effects / 100);
    }
}
