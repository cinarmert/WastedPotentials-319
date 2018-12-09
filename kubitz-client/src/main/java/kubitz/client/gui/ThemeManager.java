package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {

    public static final String RED_THEME = "RED";
    public static final String BLUE_THEME = "BLUE";

    public static void setTheme(String theme){

        if(theme.equals(RED_THEME)) {
            setThemeRED();
        }
        else if (theme.equals(BLUE_THEME)){
            setThemeBLUE();
        }
        else {
            setThemeRED();
        }
    }

    private static void setThemeBLUE() {
        Theme.backgroundImage = new ImageIcon( MainFrame.getInstance().getClass().getResource("/backgrounds/background_b.png")).getImage();
        Theme.gameBackgroundImage = new ImageIcon( MainFrame.getInstance().getClass().getResource("/backgrounds/game_background.jpg")).getImage();

        //
        Theme.goodColor = Color.GREEN;
        Theme.goodTextColor = Color.BLACK;
        Theme.badColor = Color.RED;
        Theme.badTextColor = Color.WHITE;

        //cube colors
        Theme.primaryColor = Color.BLUE;
        Theme.secondaryColor = Color.WHITE;
        Theme.nullColor = Color.CYAN;

        //clock color
        Theme.gameClockColor = Color.BLACK;

        //mask colors
        Theme.maskColorLight = new Color(0,0,0,120);
        Theme.maskColorDark = new Color(0,0,0,200);

        //fundamental colors
        Theme.borderColor = Color.BLACK;
        Theme.foregroundColor = Color.WHITE;
        Theme.backgroundColor = Color.BLUE;

        //colors
        Theme.tablePanelColor = new Color(204,204,204);
        Theme.tableHeaderColor = new Color(153,153,153);
        Theme.alternateColor1 = new Color(220,221,221);
        Theme.alternateColor2 = Color.WHITE;

        //button/tab colors
        Theme.normalColor = Color.WHITE;
        Theme.hoverColor = Color.BLUE;
        Theme.pressColor = new Color(28, 24, 125, 255);
        Theme.normalFontColor = Color.BLUE;
        Theme.hoverFontColor = Color.WHITE;
        Theme.pressFontColor = Color.WHITE;
    }

    private static void setThemeRED(){
        Theme.backgroundImage = new ImageIcon( MainFrame.getInstance().getClass().getResource("/backgrounds/background_r.png")).getImage();
        Theme.gameBackgroundImage = new ImageIcon( MainFrame.getInstance().getClass().getResource("/backgrounds/game_background.jpg")).getImage();

        //
        Theme.goodColor = Color.GREEN;
        Theme.goodTextColor = Color.BLACK;
        Theme.badColor = Color.RED;
        Theme.badTextColor = Color.WHITE;

        //cube colors
        Theme.primaryColor = Color.RED;
        Theme.secondaryColor = Color.WHITE;
        Theme.nullColor = Color.GRAY;

        //clock color
        Theme.gameClockColor = Color.BLACK;

        //mask colors
        Theme.maskColorLight = new Color(0,0,0,120);
        Theme.maskColorDark = new Color(0,0,0,200);

        //fundamental colors
        Theme.borderColor = Color.BLACK;
        Theme.foregroundColor = Color.WHITE;
        Theme.backgroundColor = Color.RED;

        //colors
        Theme.tablePanelColor = new Color(204,204,204);
        Theme.tableHeaderColor = new Color(153,153,153);
        Theme.alternateColor1 = new Color(220,221,221);
        Theme.alternateColor2 = Color.WHITE;

        //button/tab colors
        Theme.normalColor = Color.WHITE;
        Theme.hoverColor = Color.RED;
        Theme.pressColor = new Color( 140, 0, 0, 255);
        Theme.normalFontColor = Color.RED;
        Theme.hoverFontColor = Color.WHITE;
        Theme.pressFontColor = Color.WHITE;
    }
    
}
