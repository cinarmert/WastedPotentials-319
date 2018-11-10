package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    public static final String MAINMENU = "MAINMENU";
    public static final String PLAY = "PLAY";
    public static final String HOWTOPLAY = "HOWTOPLAY";
    public static final String LEADERBOARD = "LEADERBOARD";
    public static final String SETTINGS = "SETTINGS";
    public static final String CREDITS = "CREDITS";
    public static final String LOBBY = "LOBBY";
    public static final String LOBBYSETTINGS = "LOBBYSETTINGS";
    public static final String LOBBIES = "LOBBIES";
    public static final String LOBBIESFILTER = "LOBBIESFILTER";
    public static final String CREATELOBBY = "CREATELOBBY";
    public static final String CLASSICMODE = "CLASSIC";
    public static final String SWITCHMODE = "SWITCH";
    public static final String SURVIVALMODE = "SURVIVAL";
    public static final String DAILYCHALLENGEMODE = "DAILYCHALLENGE";
    public static Image background;

    public MainFrame(){
        initializeResources();
        instance = this;
    }

    private void initializeResources(){

        Dimension size = new Dimension(640,480);

        this.setSize( size);

        background = new ImageIcon(new ImageIcon(getClass().getResource("/backgrounds/background.png")).getImage()
                .getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT)).getImage();

        JPanel contentPane = new JPanel();
        contentPane.setLayout( new CardLayout() );

        contentPane.add( new MainMenuScreen( contentPane, size), MAINMENU );
        contentPane.add( new PlayScreen(contentPane,size), PLAY);
        contentPane.add( new HowToPlayScreen( contentPane, size), HOWTOPLAY);
        contentPane.add( new LeaderboardScreen( contentPane, size), LEADERBOARD);
        contentPane.add( new SettingsScreen( contentPane, size), SETTINGS);
        contentPane.add( new CreditsScreen( contentPane, size), CREDITS);
        contentPane.add( new LobbyScreen( contentPane, size), LOBBY);
        contentPane.add( new LobbySettingsScreen(contentPane, size), LOBBYSETTINGS);
        contentPane.add( new LobbiesScreen(contentPane,size), LOBBIES);
        contentPane.add( new LobbiesFilterScreen(contentPane,size), LOBBIESFILTER);
        contentPane.add( new CreateLobbyScreen(contentPane, size), CREATELOBBY);
        contentPane.add( new ClassicModeScreen( null, contentPane,size), CLASSICMODE);
        contentPane.add( new SwitchModeScreen( null,contentPane, size), SWITCHMODE);
        contentPane.add( new SurvivalModeScreen( null ,contentPane, size), SURVIVALMODE);
        contentPane.add( new DailyChallengeScreen( null,contentPane, size), DAILYCHALLENGEMODE);

        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static MainFrame getInstance(){
        return instance == null ? new MainFrame() : instance;
    }

}
