package kubitz.client.gui;

import sun.swing.SwingUtilities2;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HowToPlayScreen extends BaseScreen {

    private final Dimension gifDimension = new Dimension((getMainHeight()-(getMainHeight() / 2)) * 2,
            getMainHeight()-(getMainHeight() / 2));
    private ImageIcon controlsGIF;
    private JLabel tutorial_controls;

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
        tabbedPane.addTab("Rotation", initializeControlsTutorial());
        tabbedPane.addTab("Placement", initializePlacement());

        tabbedPane.setUI( new CustomTabbedPaneUI());
        return tabbedPane;
    }

    private JPanel initializePlacement() {
        JPanel placementTutorialPanel = new JPanel(new BorderLayout());
        placementTutorialPanel.setOpaque(false);

        ImageIcon placement = createImageIcon("/tutorials/tutorial_placement.gif");
        placement.setImage(placement.getImage().getScaledInstance((int)gifDimension.getHeight(),
                (int)gifDimension.getHeight(), Image.SCALE_DEFAULT));

        JLabel tutorial = new JLabel(placement, JLabel.CENTER);

        placementTutorialPanel.add(tutorial,BorderLayout.NORTH);

        placementTutorialPanel.add(new JTextArea("  \n You can place the cubes by clicking on the grid."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return placementTutorialPanel;
    }

    private JPanel initializeControlsTutorial() {
        JPanel controlsTutorialPanel = new JPanel(new BorderLayout());
        controlsTutorialPanel.setOpaque(false);

        controlsGIF = createImageIcon("/tutorials/tutorial_rotation.gif");
        controlsGIF.setImage(controlsGIF.getImage().getScaledInstance(  3 * getMainHeight() / 3,
                getMainHeight() / 3, Image.SCALE_DEFAULT));

        ImageIcon controls_v2 = createImageIcon("/tutorials/tutorial_rotation_v2.gif");
        controls_v2.setImage(controls_v2.getImage().getScaledInstance(  getMainHeight() / 3,
                getMainHeight() / 3, Image.SCALE_DEFAULT));

        tutorial_controls = new JLabel(controlsGIF, JLabel.CENTER);

        controlsTutorialPanel.add(tutorial_controls,BorderLayout.CENTER);

        JLabel tutorial_v2 = new JLabel(controls_v2, JLabel.CENTER);

        controlsTutorialPanel.add(tutorial_v2, BorderLayout.NORTH);

        controlsTutorialPanel.add(new JTextArea("  \n You can use W, A, S, D keys to rotate the cube to see another " +
                "face and use Q, E keys to rotate the current cube face counter-clockwise or clockwise respectively." +
                " However, you have the option to change the control keys as you wish. You can also rotate the cube by " +
                "clicking on its sides as you can see on the GIF."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.SOUTH);

        return controlsTutorialPanel;
    }

    private JPanel initializeSurvivalTutorial() {
        JPanel survivalTutorialPanel = new JPanel(new BorderLayout());
        survivalTutorialPanel.setOpaque(false);

        ImageIcon survival = createImageIcon("/tutorials/tutorial_survival.gif");
        survival.setImage(survival.getImage().getScaledInstance((int)gifDimension.getWidth(),
                (int)gifDimension.getHeight(), Image.SCALE_DEFAULT));

        JLabel tutorial = new JLabel(survival, JLabel.CENTER);

        survivalTutorialPanel.add(tutorial,BorderLayout.NORTH);

        survivalTutorialPanel.add(new JTextArea("  \n Survival is a single player mode in which you will try to solve" +
                " solve as many puzzles as you can in a time interval. You will start with a specified time, each time" +
                " you solve a puzzle, you will be given additional time. So, you will challenge yourself in" +
                " this game mode."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return survivalTutorialPanel;
    }

    private JPanel initializeChallengeTutorial() {
        JPanel challengeTutorialPanel = new JPanel(new BorderLayout());
        challengeTutorialPanel.setOpaque(false);

        ImageIcon challenge = createImageIcon("/tutorials/tutorial_challenge.gif");
        challenge.setImage(challenge.getImage().getScaledInstance((int)gifDimension.getWidth(),
                (int)gifDimension.getHeight(), Image.SCALE_DEFAULT));

        JLabel tutorial = new JLabel(challenge, JLabel.CENTER);

        challengeTutorialPanel.add(tutorial,BorderLayout.NORTH);

        challengeTutorialPanel.add(new JTextArea("  \n A daily challenge will be available each day. " +
                "The boards can be 2x2 up to 16x16 depending on the challenge. " +
                "You should place qubes to match the pattern on the game card. " +
                "After finishing the challenge of a day, your score will be added to the leaderboard. " +
                "To be the 1st player of the day, you need to complete the day's challenge in shortest time. " +
                "However, you can only play this game mode once a day."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return challengeTutorialPanel;
    }

    private JPanel initializeSwitchTutorial() {
        JPanel switchTutorialPanel = new JPanel(new BorderLayout());
        switchTutorialPanel.setOpaque(false);

        ImageIcon classic = createImageIcon("/tutorials/tutorial_switch.gif");
        classic.setImage(classic.getImage().getScaledInstance((int)gifDimension.getWidth(),
                (int)gifDimension.getHeight(), Image.SCALE_DEFAULT));

        JLabel tutorial = new JLabel(classic, JLabel.CENTER);

        switchTutorialPanel.add(tutorial,BorderLayout.NORTH);

        switchTutorialPanel.add(new JTextArea("  \n Switch mode is also a multiplayer game." +
                " In this mode the main goal is to finish the board you have before your opponent does. " +
                "The trick is, your board will be changed with your opponents board in a predefined time. " +
                "You can either unsolve the board you have or try to finish it. It is up to you and your strategy!"){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return switchTutorialPanel;
    }

    private JPanel initializeClassicTutorial() {
        JPanel classicTutorialPanel = new JPanel(new BorderLayout());
        classicTutorialPanel.setOpaque(false);

        ImageIcon classic = createImageIcon("/tutorials/tutorial_classic.gif");
        classic.setImage(classic.getImage().getScaledInstance((int)gifDimension.getWidth(),
                (int)gifDimension.getHeight(), Image.SCALE_DEFAULT));

        JLabel tutorial = new JLabel(classic, JLabel.CENTER);

        classicTutorialPanel.add(tutorial,BorderLayout.NORTH);

        classicTutorialPanel.add(new JTextArea("  \n Classic game mode is a multiplayer mode. " +
                "You can play this mode by creating a lobby or by joining a lobby. " +
                "It can be played up to 4 people and the main goal is to complete the board " +
                "according to the game card shown in the upper middle of the game screen." +
                " The first one who finishes the board wins."){{
            setLineWrap(true);
            setWrapStyleWord(true);
            setEditable(false);
            setFont(new Font("Calibri", Font.PLAIN,16));
            setBackground(Theme.tablePanelColor);
            setBorder(null);
            setAlignmentY(BOTTOM_ALIGNMENT);

        }}, BorderLayout.CENTER);

        return classicTutorialPanel;
    }

    private class CustomTabbedPaneUI extends BasicTabbedPaneUI {
        private int inclTab = 4;
        private int anchoFocoH = 4;

        @Override
        protected void installDefaults() {
            super.installDefaults();
            tabPane.setFocusable(false);

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
                g.setColor(Theme.tablePanelColor);
                g.fillRect(x,y,w,h);
            }


        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2D = (Graphics2D) g;

            if (isSelected) {
                g2D.setColor(Theme.normalColor);
            } else {
                g2D.setColor(Theme.hoverColor);

            }

            g2D.fillRect(x,y,w,h);

        }

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
            g.setFont(font);

            if (isSelected) {
                g.setColor(Theme.normalFontColor);
            } else { // tab disabled
                g.setColor(Theme.hoverFontColor);
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
            g2D.setColor(Theme.borderColor);
            g2D.drawRect(x,y,w,h);
        }

    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
