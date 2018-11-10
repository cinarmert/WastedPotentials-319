package kubitz.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;

    public PlayScreen(JPanel contentPane, Dimension size) {
        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();

    }

    private void initializeResources(){

        this.setSize( size );
        this.setBackground( new Color(0,0,0,0));
        this.setLayout( new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JLabel playLabel = new JLabel("PLAY");
        playLabel.setFont( new Font( playLabel.getFont().getName(), Font.PLAIN, 50));
        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.MAINMENU);

            }
        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.anchor = GridBagConstraints.NORTH;
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        this.add( playLabel, c);

        c.gridx = 1;
        c.gridy = 2;
        this.add( initializeButtons(),c);

    }

    private JPanel initializeButtons() {

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new GridLayout(3,1, 10, 10));
        buttonsPanel.setMaximumSize(new Dimension( getWidth()/5, 30));
        buttonsPanel.setBackground( new Color(0,0,0, 0));

        CustomButton multiplayerButton = new CustomButton("Multiplayer (Classic / Switch)");
        multiplayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.LOBBIES);

            }
        });

        CustomButton dailyChallengeButton = new CustomButton("Daily Challenge");
        dailyChallengeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.DAILYCHALLENGEMODE);

            }
        });


        CustomButton survivalButton = new CustomButton("Survival");
        survivalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.SURVIVALMODE);

            }
        });

        buttonsPanel.add(multiplayerButton);
        buttonsPanel.add(dailyChallengeButton);
        buttonsPanel.add(survivalButton);


        return buttonsPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void update() {

    }
}
