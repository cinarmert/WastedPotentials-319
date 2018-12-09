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

    public void setResolution(){
        MainFrame.resolution = Config.getResolution();
        setSize(resolution);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        ScreenManager.updateResolutions();
    }
}
