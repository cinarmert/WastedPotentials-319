package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class SettingsScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private static Dimension size;

    private JSpinner resolutionSpinner;
    private JCheckBox fullScreenCheckBox;
    private JSlider masterSlider;
    private JSlider effectsSlider;
    private JSlider musicSlider;

    private Config config;
    private Dimension resolution;
    private boolean fullScreen;
    private int masterSound;
    private int effectsSound;
    private int musicSound;

    private boolean applied;


    public SettingsScreen(JPanel contentPane, Dimension size, Config config) {
        super();
        this.contentPane = contentPane;
        this.size = size;
        this.config = config;
        this.resolution = config.getResolution();
        this.fullScreen = config.isFullScreen();
        this.masterSound = config.getMasterSound();
        this.effectsSound = config.getEffectsSound();
        this.musicSound = config.getMusicSound();
        this.applied = false;
        initializeResources();
    }

    private void initializeResources(){

        if( MainFrame.getInstance() != null & config.isFullScreen()){
            ScreenDevice.setFullScreen();
        }

        this.setSize( size );
        this.setBackground( new Color(0,0,0,0));
        this.setLayout( new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SettingsScreen.this.resolution = config.getResolution();
                SettingsScreen.this.fullScreen = config.isFullScreen();
                SettingsScreen.this.masterSound = config.getMasterSound();
                SettingsScreen.this.effectsSound = config.getEffectsSound();
                SettingsScreen.this.musicSound = config.getMusicSound();

                resolutionSpinner.setValue( new Resolution(config.getResolution().width,config.getResolution().height));
                fullScreenCheckBox.setSelected( config.isFullScreen());
                masterSlider.setValue( config.getMasterSound());
                effectsSlider.setValue(config.getEffectsSound());
                musicSlider.setValue(config.getMusicSound());

                applied = false;

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
        c.gridwidth = GridBagConstraints.REMAINDER;
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

        JTextField nickNameTextField = new JTextField(config.getName(),20);
        CustomButton submitButton = new CustomButton("SUBMIT");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                config.setName(nickNameTextField.getText());
                RESTRequestManager.login(new Account(config.getId(), nickNameTextField.getText()));
            }
        });

        nickNamePanel.add(nickNameTextField);
        nickNamePanel.add(submitButton);

        //====================================================Graphics==========================================================
        JLabel graphicsText = new JLabel("GRAPHICS");
        graphicsText.setFont( new Font( graphicsText.getFont().getName(), Font.BOLD, 16));

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.setSize( new Dimension( getWidth()/3,30));
        resolutionPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        resolutionPanel.setOpaque(false);

        JLabel resolutionText = new JLabel("Resolution");
        resolutionText.setFont( new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));

        List<Resolution> spinnerList = getResolutions();
        resolutionSpinner = new JSpinner( new SpinnerListModel( spinnerList ) );
        resolutionSpinner.setValue( new Resolution(config.getResolution().width,config.getResolution().height));
        ((JSpinner.DefaultEditor)resolutionSpinner.getEditor()).getTextField().setColumns(6);
        ((JSpinner.DefaultEditor)resolutionSpinner.getEditor()).getTextField().setEditable(false);

        resolutionSpinner.addChangeListener(new ChangeListener() {

            // for testing
            // in real game resolution will be updated after apply or save
            @Override
            public void stateChanged(ChangeEvent e) {
                resolution = (Dimension) ((JSpinner)e.getSource()).getValue();

                applied = false;
            }
        });

        resolutionPanel.add(resolutionText);
        resolutionPanel.add(resolutionSpinner);

        fullScreenCheckBox = new JCheckBox( "Full Screen", config.isFullScreen());
        fullScreenCheckBox.setFont(new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));
        fullScreenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // this is absolutely not the best way to make full screen
                fullScreen = fullScreenCheckBox.isSelected();

                applied = false;

            }
        });
        fullScreenCheckBox.setBackground(Color.WHITE);

        //====================================================Sound==========================================================
        JLabel soundText = new JLabel("SOUND");
        soundText.setFont( new Font( soundText.getFont().getName(), Font.BOLD, 16));

        Hashtable labels = new Hashtable();

        labels.put(0,new JLabel("LOW"));
        labels.put(100, new JLabel("HIGH"));

        UIManager.put("Slider.paintValue", true);

        masterSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, config.getMasterSound());
        masterSlider.setLabelTable(labels);
        masterSlider.setPaintLabels(true);
        masterSlider.setBackground(Color.WHITE);
        masterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    masterSound = (int)source.getValue();

                applied = false;

            }
        });

        effectsSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, config.getEffectsSound());
        effectsSlider.setLabelTable(labels);
        effectsSlider.setPaintLabels(true);
        effectsSlider.setBackground(Color.WHITE);
        effectsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    effectsSound = (int)source.getValue();

                applied = false;

            }
        });

        musicSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, config.getMasterSound());
        musicSlider.setLabelTable(labels);
        musicSlider.setPaintLabels(true);
        musicSlider.setBackground(Color.WHITE);
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting())
                    musicSound = (int)source.getValue();

                applied = false;

            }
        });


        //====================================================Buttons==========================================================
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout( new FlowLayout(FlowLayout.CENTER,10,10));
        buttonsPanel.setOpaque(false);

        CustomButton cancelButton = new CustomButton("CANCEL");
        cancelButton.addActionListener( new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                SettingsScreen.this.resolution = config.getResolution();
                SettingsScreen.this.fullScreen = config.isFullScreen();
                SettingsScreen.this.masterSound = config.getMasterSound();
                SettingsScreen.this.effectsSound = config.getEffectsSound();
                SettingsScreen.this.musicSound = config.getMusicSound();

                resolutionSpinner.setValue( new Resolution(config.getResolution().width,config.getResolution().height));
                fullScreenCheckBox.setSelected( config.isFullScreen());
                masterSlider.setValue( config.getMasterSound());
                effectsSlider.setValue(config.getEffectsSound());
                musicSlider.setValue(config.getMusicSound());

                applied = false;

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.MAINMENU);


            }
        });

        CustomButton saveButton = new CustomButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!applied) {
                    if (fullScreen) {
                        ScreenDevice.setFullScreen();
                    } else {
                        ScreenDevice.restoreScreen();
                    }

                    config.updateConfig(resolution, fullScreen, masterSound, effectsSound, musicSound);

                    MainFrame.getInstance().setResolution();
                }

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, MainFrame.MAINMENU);

            }
        });

        CustomButton applyButton = new CustomButton("APPLY");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                applied = true;
                if (fullScreen) {
                    ScreenDevice.setFullScreen();
                }
                else {
                    ScreenDevice.restoreScreen();
                }

                MainFrame.getInstance().setResolution();
                config.updateConfig(resolution,fullScreen,masterSound, effectsSound, musicSound);

                SettingsScreen.this.size = resolution;

            }
        });

        buttonsPanel.add(cancelButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(applyButton);


        settingsPanel.add(settingsText);
        settingsPanel.add(nickNameText);
        settingsPanel.add(nickNamePanel);
        settingsPanel.add(graphicsText);
        settingsPanel.add(resolutionPanel);
        settingsPanel.add(fullScreenCheckBox);
        settingsPanel.add(soundText);
        settingsPanel.add(masterSlider);
        settingsPanel.add(effectsSlider);
        settingsPanel.add(musicSlider);
        settingsPanel.add(buttonsPanel);
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

        //ToDo handle size change
        this.size = size;
    }

    public List<Resolution> getResolutions(){

            List<Resolution> resolutions = new ArrayList<>();

            List<DisplayMode> displayModes = new ArrayList<>(
                    Arrays.asList( GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayModes()));

            for (int i = 0; i < displayModes.size(); i++) {
                resolutions.add(new Resolution(displayModes.get(i).getWidth(), displayModes.get(i).getHeight()));
            }

            HashSet<Resolution> hashSet = new HashSet<>(resolutions);
            resolutions.clear();
            resolutions.addAll(hashSet);

            Collections.sort( resolutions );

            return resolutions;


    }

    protected class Resolution extends Dimension implements Comparable{

        public Resolution(int width, int height) {
            super(width, height);
        }

        @Override
        public String toString(){
            return width + " x " + height;
        }

        @Override
        public int compareTo(Object o) {

            if ( this.width == ((Resolution)o).width ) {
                if(this.height == ((Resolution)o).height)
                    return 0;
                else if (this.height > ((Resolution)o).height)
                    return 1;
                else
                    return -1;
            }
            else if ( this.width > ((Resolution)o).width )
                return 1;
            else
                return -1;

        }
    }

    public static class ScreenDevice{

        public static void setFullScreen(){

            Frame mainFrame = MainFrame.getInstance();

            GraphicsDevice device = mainFrame.getGraphicsConfiguration().getDevice();

            if (device.isFullScreenSupported()) {

                try {
                    device.setFullScreenWindow(mainFrame);
                    device.setDisplayMode(new DisplayMode( size.width, size.height,
                            device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
                }catch (Exception e){
                    device.setFullScreenWindow(null);
                }
            }
        }

        public static void restoreScreen(){
            MainFrame.getInstance().getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
        }


    }
}
