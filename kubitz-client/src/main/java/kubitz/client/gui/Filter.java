package kubitz.client.gui;

import java.io.*;
import java.util.Properties;

public class Filter {

    public final String FILTER_NAME = "/filter.properties";

    private Properties props;

    private boolean classicMode;
    private boolean switchMode;
    private boolean showPrivate;
    private boolean showPlaying;
    private boolean showFull;

    public Filter() {

        try {
            props = new Properties();

            InputStream inputStream = getClass().getResource(FILTER_NAME).openStream();

            props.load(inputStream);

            classicMode = Boolean.parseBoolean( props.getProperty("classicMode") );
            switchMode = Boolean.parseBoolean( props.getProperty("switchMode") );
            showPrivate = Boolean.parseBoolean( props.getProperty("showPrivate") );
            showPlaying = Boolean.parseBoolean( props.getProperty("showPlaying") );
            showFull = Boolean.parseBoolean( props.getProperty("showFull") );

        } catch (Exception e) {
            createDefaultConfig();
        }
    }

    private void createDefaultConfig(){

        try {
            props = new Properties();
            props.setProperty("classicMode", "" + true);
            props.setProperty("switchMode", "" + true);
            props.setProperty("showPrivate", "" + true);
            props.setProperty("showPlaying", "" + true);
            props.setProperty("showFull", "" + true);


            File f = new File( getClass().getResource("/").toURI().getPath() +  FILTER_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "CONFIGURATION FILE");

            classicMode = true;
            switchMode = true;
            showPrivate = true;
            showPlaying = true;
            showFull = true;
        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    public boolean getClassicMode() {
        return classicMode;
    }
    public boolean getSwitchMode() {
        return switchMode;
    }
    public boolean getShowPrivate() {
        return showPrivate;
    }
    public boolean getShowPlaying() {
        return showPlaying;
    }
    public boolean getShowFull() {
        return showFull;
    }

    public void updateConfig(boolean classicMode, boolean switchMode, boolean showPrivate,
                             boolean showPlaying, boolean showFull){

        try {
            props = new Properties();
            props.setProperty("classicMode", "" + classicMode);
            props.setProperty("switchMode", "" + switchMode);
            props.setProperty("showPrivate", "" + showPrivate);
            props.setProperty("showPlaying", "" + showPlaying);
            props.setProperty("showFull", "" + showFull);


            File f = new File( getClass().getResource("/").toURI().getPath() +  FILTER_NAME);
            OutputStream out = new FileOutputStream( f );
            props.store(out, "FILTER CONFIGURATION FILE");

            this.classicMode = classicMode;
            this.switchMode = switchMode;
            this.showPrivate = showPrivate;
            this.showPlaying = showPlaying;
            this.showFull = showFull;

        }
        catch (Exception e ) {
            e.printStackTrace();
        }

    }

    public String[] getFilteredModeList() {
        String[] filteredModeList = new String[2];
        if(classicMode)
            filteredModeList[0] = "CLASSIC";
        if(switchMode)
            filteredModeList[1] = "SWITCH";


        return filteredModeList;
    }
}
