package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class PlayScreen extends BaseScreen{


    public PlayScreen(Dimension resolution) {
        super(resolution);
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        GridBagConstraints c = new GridBagConstraints();

        JLabel playLabel = new JLabel("PLAY");
        playLabel.setFont( new Font( playLabel.getFont().getName(), Font.PLAIN, 50));

        setBackButton(true);

        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain( playLabel, c);

        c.gridx = 0;
        c.gridy = 1;
        this.addMain( initializeButtons(),c);

    }

    private JPanel initializeButtons() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(3,1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension( getMainWidth()/5, 30));
        buttonsPanel.setBackground( new Color(0,0,0, 0));

        CustomButton multiplayerButton = new CustomButton("Multiplayer (Classic / Switch)");
        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //((LobbiesScreen)ScreenManager.getScreen(ScreenManager.LOBBIES_SCREEN)).refresh();
                ScreenManager.show(ScreenManager.LOBBIES_SCREEN);
            }
        });

        CustomButton dailyChallengeButton = new CustomButton("Daily Challenge");
        dailyChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Config.getLastPlayedDailyChallenge() != 1 && (LocalDate.now().getDayOfYear() - Config.getLastPlayedDailyChallenge()) < 1){
                    JOptionPane.showMessageDialog( PlayScreen.this,
                            "You have already played the challenge of the day",
                            "Warning!",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                DailyChallengeScreen screen = new DailyChallengeScreen( MainFrame.getResolution() );

                ScreenManager.openGameScreen( screen );

            }
        });


        CustomButton survivalButton = new CustomButton("Survival");
        survivalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SurvivalModeScreen screen = new SurvivalModeScreen( MainFrame.getResolution() );

                ScreenManager.openGameScreen( screen );

            }
        });

        buttonsPanel.add(multiplayerButton);
        buttonsPanel.add(dailyChallengeButton);
        buttonsPanel.add(survivalButton);

        return buttonsPanel;
    }

}
