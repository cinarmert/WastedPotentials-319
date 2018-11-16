package kubitz.client.gui;

import java.awt.*;
import java.io.*;
import java.util.Properties;

public class Config {

    public final String PROPERTIES_NAME = "/kubitz.properties";

    private Dimension resolution;
    private boolean fullScreen;
    private int masterSound;
    private int effectsSound;
    private int musicSound;

    public Config() {

        try {
            Properties prop = new Properties();

            InputStream inputStream = getClass().getResource(PROPERTIES_NAME).openStream();
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                createDefaultConfig();
            }

            int width = Integer.parseInt( prop.getProperty("width") );
            int height = Integer.parseInt( prop.getProperty("height") );

            resolution = new Dimension( width, height);
            fullScreen = Boolean.parseBoolean( prop.getProperty("fullscreen") );
            masterSound = Integer.parseInt( prop.getProperty("master") );
            effectsSound = Integer.parseInt( prop.getProperty("effects") );
            musicSound = Integer.parseInt( prop.getProperty("music") );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDefaultConfig(){

        try {
            Properties props = new Properties();
            props.setProperty("width", ""+640);
            props.setProperty("height", ""+480);
            props.setProperty("fullscreen", ""+false);
            props.setProperty("master", ""+100);
            props.setProperty("effects", ""+100);
            props.setProperty("music", ""+100);

            File f = new File(PROPERTIES_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "CONFIGURATION FILE");
        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    public Dimension getResolution() {
        return resolution;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public int getMasterSound(){
        return masterSound;

    }

    public int getEffectsSound(){
        return effectsSound;
    }

    public int getMusicSound() {
        return musicSound;
    }

    public void updateConfig(Dimension resolution, boolean fullScreen,
                             int masterSound, int effectsSound, int musicSound){

        try {
            Properties props = new Properties();
            props.setProperty("width", ""+resolution.width);
            props.setProperty("height", ""+resolution.height);
            props.setProperty("fullscreen", ""+fullScreen);
            props.setProperty("master", ""+masterSound);
            props.setProperty("effects", ""+effectsSound);
            props.setProperty("music", ""+musicSound);

            File f = new File(PROPERTIES_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "CONFIGURATION FILE");

            this.resolution = resolution;
            this.fullScreen = fullScreen;
            this.masterSound = masterSound;
            this.effectsSound = effectsSound;
            this.musicSound = musicSound;


        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

}
