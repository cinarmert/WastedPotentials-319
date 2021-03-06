package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends BaseScreen{

    public MainMenuScreen( Dimension resolution) {
        super( resolution);
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.addMain( initializeLogo(), c);

        c.gridx = 0;
        c.gridy = 1;
        this.addMain( initializeButtons(),c);

    }

    private JPanel initializeLogo(){
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout( new GridLayout(2,1));
        //logoPanel.setMaximumSize( new Dimension( getMainWidth()/3, getMainHeight()/8));
        logoPanel.setOpaque(false);



        JLabel kubitz = new JLabel(new ImageIcon( MainFrame.getInstance().getClass().getResource("/logos/kubitz.png")) );

        JLabel version = new JLabel(new ImageIcon( MainFrame.getInstance().getClass().getResource("/logos/double_fusion.png")) );

        logoPanel.add(kubitz);
        logoPanel.add(version);


        return logoPanel;
    }

    private JPanel initializeButtons() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(5,1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension( getMainWidth(), 30));
        buttonsPanel.setOpaque(false);

        CustomButton playButton = new CustomButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ScreenManager.show(ScreenManager.PLAY_SCREEN);
            }
        });

        CustomButton howToPlayButton = new CustomButton("How to Play");
        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ScreenManager.show(ScreenManager.HOW_TO_PLAY_SCREEN);
            }
        });


        CustomButton leaderboardButton = new CustomButton("Leaderboard");
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Loading screen
                LoadingScreen loadingScreen = new LoadingScreen("Loading Leaderboard");
                Thread loadingThread = new Thread(loadingScreen);

                SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
                    @Override
                    protected Void doInBackground() {


                        if(!ScreenManager.show(ScreenManager.LEADERBOARD_SCREEN)) {
                            this.done();
                            ScreenManager.getScreen(ScreenManager.LEADERBOARD_SCREEN).onError();
                        }
                        else
                            this.done();


                        return null;
                    }

                    @Override
                    protected void done() {
                        loadingScreen.stop();
                        loadingThread.stop();
                    }
                };

                loadingThread.start();
                mySwingWorker.execute();

            }
        });

        CustomButton settingsButton = new CustomButton("Settings");
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ScreenManager.show(ScreenManager.SETTINGS_SCREEN);

            }
        });

        CustomButton creditsButton = new CustomButton("Credits");
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ScreenManager.show(ScreenManager.CREDITS_SCREEN);

            }
        });

        CustomButton quitButton = new CustomButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int quit = JOptionPane.showConfirmDialog( MainMenuScreen.this,
                        "Are you sure you want to quit ku-Bitz?",
                        "Quit?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if( quit == 0 )
                    System.exit(0);

            }
        });

        JPanel settingsAndCredits = new JPanel(new GridLayout(1,2,10,0));
        settingsAndCredits.setOpaque(false);
        settingsAndCredits.add(settingsButton);
        settingsAndCredits.add(creditsButton);

        buttonsPanel.add(playButton);
        buttonsPanel.add(howToPlayButton);
        buttonsPanel.add(leaderboardButton);
        buttonsPanel.add(settingsAndCredits);
        buttonsPanel.add(quitButton);


        return buttonsPanel;
    }

}
