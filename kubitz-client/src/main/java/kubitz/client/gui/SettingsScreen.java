package kubitz.client.gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsScreen extends JPanel implements Screen {

    JPanel contentPane;
    Dimension size;
    Resolution [] resolutions = { new Resolution(640,480),
            new Resolution(800,600),
            new Resolution(1024,768),
            new Resolution(1280,720),
            new Resolution(1280,768),
            new Resolution(1280,800),
            new Resolution(1280,960),
            new Resolution(1280,1024),
            new Resolution(1360,768),
            new Resolution(1440,900),
            new Resolution(1600,900),
            new Resolution(1600,1024),
            new Resolution(1600,1050),
            new Resolution(1920,1080) };

    boolean fullScreen = false;
    Dimension newResolution = null;



    public SettingsScreen(JPanel contentPane, Dimension size) {
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.MAINMENU);

            }
        });

        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 0.1;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add( backButton,c);

        c.weightx = 1.0;
        c.gridx = 1;
        c.gridy = 0;
        this.add( initializeSettings(),c);

    }

    private JPanel initializeSettings() {

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout( new GridLayout(11,1, 10, 5));
        settingsPanel.setMinimumSize(new Dimension( getWidth()*4/5, getHeight()-20));
        settingsPanel.setBackground( new Color(0,200,0, 0));

        JLabel settingsText = new JLabel("SETTINGS");
        settingsText.setFont( new Font(settingsText.getFont().getName(), Font.BOLD, 34));

        //====================================================Nick Name==========================================================
        JLabel nickNameText = new JLabel("NICK NAME");
        nickNameText.setFont( new Font( nickNameText.getFont().getName(), Font.BOLD, 16));

        JPanel nickNamePanel = new JPanel();
        nickNamePanel.setSize( new Dimension( getWidth()/3,30));
        nickNamePanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        nickNamePanel.setOpaque(false);

        JTextField nickNameTextField = new JTextField("MrHardDick",20);
        CustomButton summitButton = new CustomButton("SUMMIT");

        nickNamePanel.add(nickNameTextField);
        nickNamePanel.add(summitButton);

        //====================================================Graphics==========================================================
        JLabel graphicsText = new JLabel("GRAPHICS");
        graphicsText.setFont( new Font( graphicsText.getFont().getName(), Font.BOLD, 16));

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.setSize( new Dimension( getWidth()/3,30));
        resolutionPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        resolutionPanel.setOpaque(false);

        JLabel resolutionText = new JLabel("Resolution");
        resolutionText.setFont( new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));

        JSpinner resolutionSpinner = new JSpinner( new SpinnerListModel( resolutions ));
        ((JSpinner.DefaultEditor)resolutionSpinner.getEditor()).getTextField().setColumns(6);
        resolutionSpinner.addChangeListener(new ChangeListener() {

            // for testing
            // in real game resolution will be updated after apply or save
            @Override
            public void stateChanged(ChangeEvent e) {
                newResolution = (Dimension) ((JSpinner)e.getSource()).getValue();
                MainFrame.getInstance().updateResolution(newResolution);

                if (fullScreen){
                    GraphicsDevice device = GraphicsEnvironment
                            .getLocalGraphicsEnvironment().getScreenDevices()[0];

                    device.setDisplayMode( new DisplayMode( newResolution.width, newResolution.height,
                            device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
                    device.setFullScreenWindow(MainFrame.getInstance());
                }

            }
        });

        resolutionPanel.add(resolutionText);
        resolutionPanel.add(resolutionSpinner);

        JCheckBox fullScreenCheckBox = new JCheckBox( "Full Screen", fullScreen);
        fullScreenCheckBox.setFont(new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));
        fullScreenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // this is absolutely not the best way to make full screen
                fullScreen = fullScreenCheckBox.isSelected();

                GraphicsDevice device = GraphicsEnvironment
                        .getLocalGraphicsEnvironment().getScreenDevices()[0];

                if (fullScreen) {
                    device.setFullScreenWindow(MainFrame.getInstance());
                    device.setDisplayMode( new DisplayMode( size.width, size.height,
                            device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));

                }
                else {
                    device.setFullScreenWindow(null);
                }
            }
        });

        //====================================================Sound==========================================================
        JLabel soundText = new JLabel("SOUND");
        soundText.setFont( new Font( soundText.getFont().getName(), Font.BOLD, 16));


        settingsPanel.add(settingsText);
        settingsPanel.add(nickNameText);
        settingsPanel.add(nickNamePanel);
        settingsPanel.add(graphicsText);
        settingsPanel.add(resolutionPanel);
        settingsPanel.add(fullScreenCheckBox);
        settingsPanel.add(soundText);
        return settingsPanel;
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
        this.size = size;
    }

    private class Resolution extends Dimension{

        public Resolution(int width, int height) {
            super(width, height);
        }

        @Override
        public String toString(){
            return width + " x " + height;
        }
    }
}
