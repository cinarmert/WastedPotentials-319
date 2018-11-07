package kubitz.client.gui;

import sun.management.counter.perf.PerfLongArrayCounter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JPanel implements Screen {

    private final String PLAY = "PLAY";
    private final String HOWTOPLAY = "HOW TO PLAY";
    private final String LEADERBOARD = "LEADERBOARD";
    private final String SETTING = "SETTING";
    private final String CREDITS = "CREDITS";
    private final String QUIT = "QUIT";


    JButton playButton;
    JButton howToPlayButton;
    JButton leaderboardButton;
    JButton settingButton;
    JButton creditsButton;
    JButton quitButton;

    private JPanel contentPane;
    private Dimension buttonSize;

    public MainMenuScreen(JPanel contentPane, Dimension size) {

        this.contentPane = contentPane;
        buttonSize = new Dimension(size.width/2,30);

        JPanel name = new JPanel();
        JPanel version = new JPanel();
        JPanel buttons = new JPanel();
        MainMenuActionListener mainMenuActionListener = new MainMenuActionListener();

        playButton = new JButton( PLAY );
        howToPlayButton = new JButton( HOWTOPLAY );
        leaderboardButton = new JButton( LEADERBOARD );
        settingButton = new JButton( SETTING );
        creditsButton = new JButton( CREDITS );
        quitButton = new JButton( QUIT );

        playButton.setName( PLAY);
        howToPlayButton.setName( HOWTOPLAY);
        leaderboardButton.setName( LEADERBOARD);
        settingButton.setName( SETTING);
        creditsButton.setName(CREDITS);
        quitButton.setName(QUIT);

        playButton.setMaximumSize( buttonSize);
        howToPlayButton.setMaximumSize( buttonSize);
        leaderboardButton.setMaximumSize( buttonSize);
        settingButton.setMaximumSize( buttonSize);
        creditsButton.setMaximumSize( buttonSize);
        quitButton.setMaximumSize( buttonSize);

        playButton.addActionListener( mainMenuActionListener);
        howToPlayButton.addActionListener( mainMenuActionListener);
        leaderboardButton.addActionListener( mainMenuActionListener);
        settingButton.addActionListener( mainMenuActionListener);
        creditsButton.addActionListener( mainMenuActionListener);
        quitButton.addActionListener( mainMenuActionListener);

        buttons.setLayout( new BoxLayout( buttons, BoxLayout.PAGE_AXIS) );
        playButton.setAlignmentX( CENTER_ALIGNMENT);
        howToPlayButton.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardButton.setAlignmentX(CENTER_ALIGNMENT);
        settingButton.setAlignmentX(CENTER_ALIGNMENT);
        creditsButton.setAlignmentX(CENTER_ALIGNMENT);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);

        buttons.add( playButton);
        buttons.add( howToPlayButton);
        buttons.add( leaderboardButton);
        buttons.add( settingButton);
        buttons.add( creditsButton);
        buttons.add( quitButton);


        setLayout(new BoxLayout( this, BoxLayout.PAGE_AXIS) );

        add(name);
        add(version);
        add(buttons);

        setPreferredSize( size);
    }

    @Override
    public void update() {

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        Dimension buttonSize = new Dimension(getWidth()/5, 30);


        playButton.setMaximumSize(buttonSize);
        howToPlayButton.setMaximumSize(buttonSize);
        leaderboardButton.setMaximumSize(buttonSize);
        settingButton.setMaximumSize(buttonSize);
        creditsButton.setMaximumSize(buttonSize);
        quitButton.setMaximumSize(buttonSize);




    }

    private class MainMenuActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String name = ((JButton) e.getSource()).getName();

            switch (name)
            {
                case PLAY:
                    System.out.println(PLAY);
                    break;
                case HOWTOPLAY:
                    System.out.println( HOWTOPLAY);
                    break;
                case LEADERBOARD:
                    System.out.println(LEADERBOARD);
                    break;
                case SETTING:
                    System.out.println(SETTING);
                    break;
                case CREDITS:
                    System.out.println(CREDITS);
                    break;
                case QUIT:
                    System.out.println(QUIT);
                    break;
                default:
                    System.out.println("DEFAULT");

            }




        }
    }
}
