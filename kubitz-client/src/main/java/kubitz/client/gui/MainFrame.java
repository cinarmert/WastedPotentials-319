package kubitz.client.gui;

import kubitz.client.controllers.MoveController;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;

import javax.swing.*;
import java.awt.*;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.UUID;


public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private JPanel contentPane;
    private MoveController moveController;

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

    public static final int CLASSICMODEINDEX = 11;
    public static final int SWITCHMODEINDEX = 12;
    public static final int SURVIVALMODEINDEX = 13;
    public static final int DAILYCHALLENGEMODEINDEX = 14;

    public static Image background;
    private Dimension size;
    Config config;
    private NetworkInterface networkInterface;
    private Account account;
    Filter filter;
    public static LobbyScreen lobbyScreen;

    public MainFrame(){
        this.config = new Config();
        filter = new Filter();
        initializeResources();
        initializeAccount();
        instance = this;
    }

    private void initializeAccount() {

        if(config.isRegistered()){
            account = new Account(config.getId(), config.getName());
            return;
        }

        try {
            networkInterface = NetworkInterface.getByName("en0");
            String mac = new String(networkInterface.getHardwareAddress());
            String name = UUID.randomUUID().toString();
            account = new Account(mac, name);
            config.setId(mac);
            config.setName(name);
            RESTRequestManager.login(account);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private void initializeResources(){

        size = config.getResolution();

        this.setSize( size);

        background = new ImageIcon(getClass().getResource("/backgrounds/background.png")).getImage();

        contentPane = new JPanel();
        contentPane.setLayout( new CardLayout() );

        lobbyScreen = new LobbyScreen(contentPane,size);

        contentPane.add( new MainMenuScreen( contentPane, size), MAINMENU );
        contentPane.add( new PlayScreen(contentPane,size), PLAY);
        contentPane.add( new HowToPlayScreen( contentPane, size), HOWTOPLAY);
        contentPane.add( new LeaderboardScreen( contentPane, size), LEADERBOARD);
        contentPane.add( new SettingsScreen( contentPane, size, config), SETTINGS);
        contentPane.add( new CreditsScreen( contentPane, size), CREDITS);
        contentPane.add( lobbyScreen, LOBBY);
        contentPane.add( new LobbySettingsScreen(contentPane, size), LOBBYSETTINGS);
        contentPane.add( new LobbiesScreen(contentPane,size, filter), LOBBIES);
        contentPane.add( new LobbiesFilterScreen(contentPane,size, filter), LOBBIESFILTER);
        contentPane.add( new CreateLobbyScreen(contentPane, size), CREATELOBBY);
        contentPane.add( new ClassicModeScreen(  contentPane,size), CLASSICMODE, CLASSICMODEINDEX);
        contentPane.add( new SwitchModeScreen( contentPane, size), SWITCHMODE, SWITCHMODEINDEX);
        contentPane.add( new SurvivalModeScreen( contentPane, size), SURVIVALMODE, SURVIVALMODEINDEX);
        contentPane.add( new DailyChallengeScreen( contentPane, size), DAILYCHALLENGEMODE, DAILYCHALLENGEMODEINDEX);

        this.setContentPane(contentPane);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        moveController = new MoveController();
        addKeyListener(moveController);
    }

    public MoveController getMoveController(){
        return moveController;
    }

    public static MainFrame getInstance(){
        return instance;// == null ? new MainFrame() : instance;
    }

    public Dimension getResolution(){
        return size;
    }

    public void setResolution(){
        this.size = config.getResolution();
        setSize(size);
    }

}
