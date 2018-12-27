package kubitz.client.gui;

import kubitz.client.controllers.MoveController;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.sound.SoundManager;
import kubitz.client.storage.Account;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.UUID;


public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private static Dimension resolution;

    private MoveController moveController;
    private Account account;

    public MainFrame(){
        instance = this;

        ThemeManager.setTheme(Config.getTheme());
        new SoundManager();
        initializeAccount();
        initializeResources();

        SoundManager.initializeVolumes((double)Config.getMasterSound(),
                (double)Config.getEffectsSound(), (double)Config.getMusicSound());
        SoundManager.startBackgroundMusic();
    }

    private void initializeAccount() {

        if(Config.isRegistered()){
            account = new Account(Config.getId());
            return;
        }

        try {
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            String name = UUID.randomUUID().toString();
            account = new Account(name);
            Config.setId(name);
            RESTRequestManager.login(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeResources(){

        resolution = Config.getResolution();
        moveController = new MoveController();

        if( Config.isFullScreen()){
            SettingsScreen.setFullScreen();
        }

        this.setContentPane(new ScreenManager());
        this.setSize( resolution);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                int quit = JOptionPane.showConfirmDialog( MainFrame.this,
                        "Are you sure you want to quit ku-Bitz?",
                        "Quit?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if( quit == 0 ) {
                    LobbyScreen lobbyScreen = (LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN);
                    if (lobbyScreen.getCurrentLobby() != null) {
                        WebSocketManager.sendLeaveLobbyMessage(lobbyScreen.getCurrentLobby().getId());
                    }
                    System.exit(0);
                }
            }
        });
        this.setResizable(false);
        this.setVisible(true);

        addKeyListener(moveController);
        setFocusable(true);
        requestFocusInWindow();
    }

    public MoveController getMoveController(){
        return moveController;
    }

    public static MainFrame getInstance(){
        return instance == null ? new MainFrame() : instance;
    }

    public static Dimension getResolution(){
        return resolution;
    }

    public void setResolution(){
        MainFrame.resolution = Config.getResolution();
        setSize(resolution);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        ScreenManager.updateResolutions();
    }
}
