package kubitz.client.gui;

import sun.swing.SwingUtilities2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HowToPlayScreen extends BaseScreen {

    public HowToPlayScreen(Dimension resolution) {

        super(resolution);
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        setBackButton(true);

        GridBagConstraints c = new GridBagConstraints();

        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        this.addMain( initializeHowToPlay(),c);

    }

    private JPanel initializeHowToPlay() {

        JPanel howToPlayPanel = new JPanel();
        howToPlayPanel.setLayout( new GridLayout(1,1, 10, 10));
        howToPlayPanel.setMaximumSize(new Dimension( getMainWidth()-20, 30));
        howToPlayPanel.setOpaque(false);
        howToPlayPanel.add(initializeTabbedPane());

        return howToPlayPanel;
    }

    private JTabbedPane initializeTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(getMainWidth()-(getMainWidth()/5), getMainHeight()-(getMainHeight()/5)));

        tabbedPane.addTab("Classic", initializeClassicTutorial());
        tabbedPane.addTab("Switch", initializeSwitchTutorial());
        tabbedPane.addTab("Daily Challenge", initializeChallengeTutorial());
        tabbedPane.addTab("Survival", initializeSurvivalTutorial());
        tabbedPane.addTab("Controls", initializeControlsTutorial());

        tabbedPane.setUI( new CustomTabbedPaneUI());
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

        Image reSizedKeys = keys.getScaledInstance(getMainWidth()/4, getMainHeight()/4, Image.SCALE_SMOOTH);
        Image reSizedMouse = mouse.getScaledInstance(getMainWidth()/4, getMainHeight()/4, Image.SCALE_SMOOTH);
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

        Image reSizedTutorialImage = tutorialImage.getScaledInstance(getMainWidth()-(getMainWidth()/5),
                getMainHeight()-(getMainHeight()/2), Image.SCALE_SMOOTH);
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
        Image reSizedTutorialImage = tutorialImage.getScaledInstance(getMainWidth()-(getMainWidth()/5),
                getMainHeight()-(getMainHeight()/2), Image.SCALE_SMOOTH);
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

    private class CustomTabbedPaneUI extends BasicTabbedPaneUI {
        private Color selectColor;
        private Color deSelectColor;
        private int inclTab = 4;
        private int anchoFocoH = 4;

        @Override
        protected void installDefaults() {
            super.installDefaults();
            selectColor = Color.WHITE;
            deSelectColor = Color.RED;

        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            int width = tabPane.getWidth();
            int height = tabPane.getHeight();
            Insets insets = tabPane.getInsets();

            int x = insets.left;
            int y = insets.top;
            int w = width - insets.right - insets.left;
            int h = height - insets.top - insets.bottom;

            y += calculateTabAreaHeight(tabPlacement, runCount, maxTabHeight);
            h -= (y - insets.top);

            if ( tabPane.getTabCount() > 0  ) {
                g.setColor(selectColor);
                g.fillRect(x,y,w,h);
            }


        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2D = (Graphics2D) g;

            if (isSelected) {
                g2D.setColor(selectColor);
            } else {
                g2D.setColor(deSelectColor);

            }

            g2D.fillRect(x,y,w,h);

        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
            g.setFont(font);

            if (isSelected) {
                g.setColor(deSelectColor);
            } else { // tab disabled
                g.setColor(selectColor);
            }

            int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);


            SwingUtilities2.drawStringUnderlineCharAt(tabPane, g,
                    title, mnemIndex,
                    textRect.x, textRect.y + metrics.getAscent());
        }

        @Override
        protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
            return 30 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
        }

        @Override
        protected int calculateTabHeight(int tabPlacement, int tabIndex, int fontHeight) {
            if (tabPlacement == LEFT || tabPlacement == RIGHT) {
                return super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
            } else {
                return anchoFocoH + super.calculateTabHeight(tabPlacement, tabIndex, fontHeight);
            }
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2D = (Graphics2D) g;

            g2D.setStroke(new BasicStroke(2));
            g2D.setColor(Color.BLACK);
            g2D.drawRect(x,y,w,h);
        }

    }
}
