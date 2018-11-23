package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HowToPlayScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;

    public HowToPlayScreen(JPanel contentPane, Dimension size) {

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

        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(e -> {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.MAINMENU);

        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0,0,0,0);
        this.add( initializeHowToPlay(),c);

    }

    private JPanel initializeHowToPlay() {

        JPanel howToPlayPanel = new JPanel();
        howToPlayPanel.setLayout( new GridLayout(1,1, 10, 10));
        howToPlayPanel.setMaximumSize(new Dimension( getWidth()-20, 30));
        howToPlayPanel.setBackground( new Color(0,0,0, 0));
        howToPlayPanel.add(initializeTabbedPane());


        return howToPlayPanel;
    }

    private JTabbedPane initializeTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new LineBorder(Color.BLACK,2));
        tabbedPane.setBackground(new Color(204,204,204));
        tabbedPane.setOpaque(true);
        tabbedPane.setPreferredSize(new Dimension(size.width-(size.width/5), size.height-(size.height/5)));
        tabbedPane.addTab("Classic", initializeClassicTutorial());
        tabbedPane.addTab("Switch", initializeSwitchTutorial());
        tabbedPane.addTab("Daily Challenge", initializeChallengeTutorial());
        tabbedPane.addTab("Survival", initializeSurvivalTutorial());
        tabbedPane.addTab("Controls", initializeControlsTutorial());

        return tabbedPane;
    }

    private JPanel initializeControlsTutorial() {
        JPanel controlsTutorialPanel = new JPanel(new GridLayout(2, 2));
        controlsTutorialPanel.setOpaque(false);
        JLabel mouseIcon = new JLabel();
        JLabel keysIcon = new JLabel();
        BufferedImage keys = null;
        BufferedImage mouse = null;
        try {
            keys = ImageIO.read(getClass().getResource("/tutorials/keys.png"));
            mouse = ImageIO.read(getClass().getResource("/tutorials/mouse.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image reSizedKeys = keys.getScaledInstance(size.width/4,size.height/4, Image.SCALE_SMOOTH);
        Image reSizedMouse = mouse.getScaledInstance(size.width/4, size.height/4, Image.SCALE_SMOOTH);
        mouseIcon.setIcon(new ImageIcon(reSizedMouse));
        keysIcon.setIcon(new ImageIcon(reSizedKeys));
        controlsTutorialPanel.add(keysIcon);
        controlsTutorialPanel.add(new JTextArea("You can use W, A, S, D keys to rotate the cube to see another face" +
                "and use Q, E keys to rotate the current cube face counter-clockwise or clockwise respectively"){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(new Color(204,204,204));
            setBorder(new LineBorder(Color.BLACK,2));

        }});
        controlsTutorialPanel.add(mouseIcon);
        controlsTutorialPanel.add(new JTextArea("You can use your mouse's left click on a grid to place the cube"){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(new Color(204,204,204));
            setBorder(new LineBorder(Color.BLACK,2));
        }});


        return controlsTutorialPanel;
    }

    private JPanel initializeSurvivalTutorial() {
        JPanel survivalTutorialPanel = new JPanel(new BorderLayout());
        survivalTutorialPanel.setOpaque(false);
        JLabel tutorial = new JLabel();
        BufferedImage tutorialImage = null;
        try {
            tutorialImage = ImageIO.read(getClass().getResource("/tutorials/survival_tutorial.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image reSizedTutorialImage = tutorialImage.getScaledInstance(size.width-(size.width/5),
                size.height-(size.height/2), Image.SCALE_SMOOTH);
        tutorial.setIcon(new ImageIcon(reSizedTutorialImage));
        survivalTutorialPanel.add(tutorial,BorderLayout.NORTH);

        survivalTutorialPanel.add(new JTextArea("\n     Survival is a single player mode in which you will " +
                "try to solve solve as many puzzles as you can in a time interval. You will start with a " +
                "specified time, each time you solve a puzzle, you will be given additional time. " +
                "So, you will challenge yourself in this game mode."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(new Color(204,204,204));
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return survivalTutorialPanel;
    }

    private JPanel initializeChallengeTutorial() {
        JPanel challengeTutorialPanel = new JPanel(new BorderLayout());
        challengeTutorialPanel.setOpaque(false);
        JLabel tutorial = new JLabel();
        BufferedImage tutorialImage = null;
        try {
            tutorialImage = ImageIO.read(getClass().getResource("/tutorials/daily_challenge_tutorial.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image reSizedTutorialImage = tutorialImage.getScaledInstance(size.width-(size.width/5),
                size.height-(size.height/2), Image.SCALE_SMOOTH);
        tutorial.setIcon(new ImageIcon(reSizedTutorialImage));
        challengeTutorialPanel.add(tutorial,BorderLayout.NORTH);

        challengeTutorialPanel.add(new JTextArea("  \n Each day there is a daily challenge. " +
                "The grid is can be 2x2 up to 16x16 depending on the challenge. " +
                "You place squares to match the pattern on the card. After finishing the day's challenge," +
                " your score will be added to the leaderboard. " +
                "To be the 1st player of the day, you need to complete the day's challenge in shortest time."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(new Color(204,204,204));
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return challengeTutorialPanel;
    }

    private JPanel initializeSwitchTutorial() {
        JPanel switchTutorialPanel = new JPanel();
        switchTutorialPanel.setOpaque(false);

        switchTutorialPanel.add(new JLabel("Switch Tutorial"));

        return switchTutorialPanel;
    }

    private JPanel initializeClassicTutorial() {
        JPanel classicTutorialPanel = new JPanel();
        classicTutorialPanel.setOpaque(false);

        classicTutorialPanel.add(new JLabel("Classic Tutorial"));

        return classicTutorialPanel;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.background, 0, 0, getWidth(), getHeight(), this);
    }


    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {

        //ToDo handle size change
        this.size = size;
    }
}
