package kubitz.client.gui;

import kubitz.client.controllers.MoveController;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.sound.SoundManager;
import kubitz.client.storage.Account;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Stack;
import java.util.UUID;


public class MainFrame extends JFrame {

    private static MainFrame instance = null;
    private static Image background;
    private static Dimension resolution;

    private MoveController moveController;
    private SoundManager soundManager;
    private Account account;

    public MainFrame(){
        instance = this;

        soundManager = new SoundManager();
        soundManager.initializeVolumes((double)Config.getMasterSound(),
                (double)Config.getEffectsSound(), (double)Config.getMusicSound());

        initializeAccount();
        initializeResources();

        soundManager.startBackgroundMusic();
    }

    private void initializeAccount() {

        if(Config.isRegistered()){
            account = new Account(Config.getId(), Config.getName());
            return;
        }

        try {
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            String mac = new String(networkInterface.getHardwareAddress());
            String name = UUID.randomUUID().toString();
            account = new Account(mac, name);
            Config.setId(mac);
            Config.setName(name);
            RESTRequestManager.login(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeResources(){

        resolution = Config.getResolution();
        background = new ImageIcon(getClass().getResource("/backgrounds/background.png")).getImage();
        moveController = new MoveController();

        if( Config.isFullScreen()){
            SettingsScreen.setFullScreen();
        }

        this.setContentPane(new ScreenManager());
        this.setSize( resolution);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        addKeyListener(moveController);
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

    public static Image getBackgroundImage() {
        return background;
    }

    public void setResolution(){
        MainFrame.resolution = Config.getResolution();
        setSize(resolution);

        ScreenManager.updateResolutions();
    }

    public SoundManager getSoundManager()
    {
        return soundManager;
    }
}
