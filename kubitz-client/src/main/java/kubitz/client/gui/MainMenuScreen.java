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
        this.add( initializeLogo(), c);

        c.gridx = 0;
        c.gridy = 1;
        this.add( initializeButtons(),c);

    }

    private JPanel initializeLogo(){
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout( new GridLayout(2,1));
        logoPanel.setMaximumSize( new Dimension( getRightWidth()/3, getRightHeight()/8));
        logoPanel.setBackground( new Color(0,0,0,0));

        JLabel kubitz = new JLabel("ku-Bitz");
        kubitz.setFont( new Font( kubitz.getFont().getName(), Font.PLAIN, 50));

        JLabel version = new JLabel("Menthol Edition");
        version.setFont( new Font( kubitz.getFont().getName(), Font.PLAIN, 20));

        logoPanel.add(kubitz);
        logoPanel.add(version);


        return logoPanel;
    }

    private JPanel initializeButtons() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(5,1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension( getRightWidth(), 30));
        buttonsPanel.setBackground( new Color(0,0,0, 0));

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

                ScreenManager.getScreen(ScreenManager.LEADERBOARD_SCREEN).update();
                ScreenManager.show(ScreenManager.LEADERBOARD_SCREEN);

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
        settingsAndCredits.setBackground( new Color(0,0,0,0));
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
