package kubitz.client.gui;

import kubitz.client.storage.Account;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {

    private static final String PROPERTIES_NAME = "/kubitz.properties";
    private static Properties props = readProps();

    private static Dimension resolution;
    private static boolean fullScreen;
    private static int masterSound;
    private static int effectsSound;
    private static int musicSound;
    private static String id;
    private static String name;
    private static long lastPlayedDailyChallenge;
    private static String theme;
    private static String server;

    private static Properties readProps(){
        Properties props;

        try {
            props = new Properties();

            InputStream inputStream = Config.class.getResource(PROPERTIES_NAME).openStream();

            props.load(inputStream);

            int width = Integer.parseInt( props.getProperty("width") );
            int height = Integer.parseInt( props.getProperty("height") );

            Config.resolution = new Dimension( width, height);
            Config.fullScreen = Boolean.parseBoolean( props.getProperty("fullscreen") );
            Config.masterSound = Integer.parseInt( props.getProperty("master") );
            Config.effectsSound = Integer.parseInt( props.getProperty("effects") );
            Config.musicSound = Integer.parseInt( props.getProperty("music") );
            Config.id = props.getProperty("id");
            Config.name = props.getProperty("name");
            Config.lastPlayedDailyChallenge = Long.parseLong(props.getProperty("lastPlayedDailyChallenge"));
            Config.theme = props.getProperty("theme");
            Config.server = props.getProperty("server");

            if ( resolution == null || theme  == null  || server == null){
                return createDefaultConfig();
            }

            return props;

        } catch (Exception e) {
            return createDefaultConfig();
        }

    }

    private static Properties createDefaultConfig(){

        try {
            props = new Properties();

            DisplayMode displayMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();
            props.setProperty("width", ""+displayMode.getWidth());
            props.setProperty("height", ""+displayMode.getHeight());
            props.setProperty("fullscreen", ""+false);
            props.setProperty("master", ""+100);
            props.setProperty("effects", ""+100);
            props.setProperty("music", ""+100);
            props.setProperty("lastPlayedDailyChallenge", ""+(-1));
            props.setProperty("theme", ThemeManager.RED_THEME);
            props.setProperty("server", "http://40.89.169.29:8083");

            updateFile();

            Config.resolution = new Dimension( displayMode.getWidth(), displayMode.getHeight());
            Config.fullScreen = false;
            Config.masterSound = 100;
            Config.effectsSound = 100;
            Config.musicSound = 100;
            Config.theme = props.getProperty("theme");
            Config.server =  "http://40.89.169.29:8083";

            return props;
        }
        catch (Exception e ) {
            return null;
        }

    }

    private static void updateFile(){
        try {
            File f = new File( Config.class.getResource("/").toURI().getPath() +  PROPERTIES_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "CONFIGURATION FILE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setTheme( String theme){
        props.setProperty("theme", theme);
        Config.theme = props.getProperty("theme");
        updateFile();
    }

    public static String getTheme(){
        return theme;
    }

    public static long getLastPlayedDailyChallenge() {
        return lastPlayedDailyChallenge;
    }

    public static void setLastPlayedDailyChallenge(long lastPlayedDailyChallenge) {
        props.setProperty("lastPlayedDailyChallenge", Long.toString(lastPlayedDailyChallenge));
        Config.lastPlayedDailyChallenge = lastPlayedDailyChallenge;
        updateFile();
    }

    public static boolean isRegistered(){
        return props.containsKey("id") && getId() != null;
    }

    public static String getId(){
        return id;
    }

    public static void setId(String id){
        props.setProperty("id", id);
        Config.id = id;
        updateFile();
    }

    public static void setName(String name){
        props.setProperty("name", name);
        Config.name = name;
        updateFile();
    }

    public static String getName(){
        return name;
    }

    public static Dimension getResolution() {
        return resolution;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static int getMasterSound(){
        return masterSound;

    }

    public static int getEffectsSound(){
        return effectsSound;
    }

    public static Account getAccount() {
        return new Account(getId(), getName());
    }

    public static int getMusicSound() {
        return musicSound;
    }

    public static void updateConfig(Dimension resolution, boolean fullScreen,
                             int masterSound, int effectsSound, int musicSound){

        try {
            props.setProperty("width", ""+resolution.width);
            props.setProperty("height", ""+resolution.height);
            props.setProperty("fullscreen", ""+fullScreen);
            props.setProperty("master", ""+masterSound);
            props.setProperty("effects", ""+effectsSound);
            props.setProperty("music", ""+musicSound);

            updateFile();

            Config.resolution = resolution;
            Config.fullScreen = fullScreen;
            Config.masterSound = masterSound;
            Config.effectsSound = effectsSound;
            Config.musicSound = musicSound;


        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    public static void setServer(String s)
    {
        props.setProperty("server", s);
        server = s;
        updateFile();
    }

    public static String getServer()
    {
        if(props == null)
            props = readProps();
        return server;
    }

}
