package kubitz.client.gui;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    public final String PROPERTIES_NAME = "/kubitz.properties";

    Properties props;

    private Dimension resolution;
    private boolean fullScreen;
    private int masterSound;
    private int effectsSound;
    private int musicSound;
    private static String id;
    private static String name;

    public Config() {

        try {
            props = new Properties();

            InputStream inputStream = getClass().getResource(PROPERTIES_NAME).openStream();

            props.load(inputStream);

            int width = Integer.parseInt( props.getProperty("width") );
            int height = Integer.parseInt( props.getProperty("height") );

            resolution = new Dimension( width, height);
            fullScreen = Boolean.parseBoolean( props.getProperty("fullscreen") );
            masterSound = Integer.parseInt( props.getProperty("master") );
            effectsSound = Integer.parseInt( props.getProperty("effects") );
            musicSound = Integer.parseInt( props.getProperty("music") );
            id = props.getProperty("id");
            name = props.getProperty("name");

        } catch (Exception e) {
            createDefaultConfig();
        }
    }

    private void createDefaultConfig(){

        try {
            props = new Properties();

            DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayModes()[0];
            props.setProperty("width", ""+displayMode.getWidth());
            props.setProperty("height", ""+displayMode.getHeight());
            props.setProperty("fullscreen", ""+false);
            props.setProperty("master", ""+100);
            props.setProperty("effects", ""+100);
            props.setProperty("music", ""+100);

            File f = new File( getClass().getResource("/").toURI().getPath() +  PROPERTIES_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "CONFIGURATION FILE");

            resolution = new Dimension( 800, 600);
            fullScreen = false;
            masterSound = 100;
            effectsSound = 100;
            musicSound = 100;
        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    public boolean isRegistered(){
        return props.containsKey("id") && getId() != null;
    }

    public static String getId(){
        return id;
    }

    public void setId(String id){
        props.setProperty("id", id);
        this.id = id;
    }

    public void setName(String name){
        props.setProperty("name", name);
        this.name = name;
    }

    public static String getName(){
        return name;
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
            props.setProperty("width", ""+resolution.width);
            props.setProperty("height", ""+resolution.height);
            props.setProperty("fullscreen", ""+fullScreen);
            props.setProperty("master", ""+masterSound);
            props.setProperty("effects", ""+effectsSound);
            props.setProperty("music", ""+musicSound);

            File f = new File( getClass().getResource(PROPERTIES_NAME).toURI() );
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
