package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class SettingsScreen extends BaseScreen{

    private JSpinner resolutionSpinner;
    private JCheckBox fullScreenCheckBox;
    private JSlider masterSlider;
    private JSlider effectsSlider;
    private JSlider musicSlider;
    private JTextField nickNameTextField;

    private Dimension resolution;
    private boolean fullScreen;
    private int masterSound;
    private int effectsSound;
    private int musicSound;

    private boolean applied;

    public SettingsScreen(Dimension resolution) {
        super(resolution);
        this.applied = true;
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        this.resolution = Config.getResolution();
        this.fullScreen = Config.isFullScreen();
        this.masterSound = Config.getMasterSound();
        this.effectsSound = Config.getEffectsSound();
        this.musicSound = Config.getMusicSound();

        setBackButton(true);

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain( initializeSettings(),c);

    }

    private JPanel initializeSettings() {

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout( new GridLayout(16,1, 10, 5));
        settingsPanel.setOpaque(false);
        settingsPanel.setPreferredSize(new Dimension(getResolution().width/2, getResolution().height));

        JLabel settingsText = new JLabel("SETTINGS");
        settingsText.setFont( new Font(settingsText.getFont().getName(), Font.BOLD, 34));

        //====================================================Nick Name==========================================================
        JLabel nickNameText = new JLabel("NICK NAME");
        nickNameText.setFont( new Font( nickNameText.getFont().getName(), Font.BOLD, 16));

        JPanel nickNamePanel = new JPanel();
        nickNamePanel.setSize( new Dimension( getMainWidth()/3,30));
        nickNamePanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        nickNamePanel.setOpaque(false);

        nickNameTextField = new JTextField(Config.getName(),20);
        nickNameTextField.setBackground(Color.GREEN);
        nickNameTextField.setForeground(Color.BLACK);
        nickNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);

                if (Config.getName().equals( nickNameTextField.getText() ) ){
                    nickNameTextField.setBackground(Color.GREEN);
                    nickNameTextField.setForeground(Color.BLACK);
                }
                else{
                    nickNameTextField.setBackground(Color.RED);
                    nickNameTextField.setForeground(Color.WHITE);
                }

            }
        });

        CustomButton submitButton = new CustomButton("SUBMIT");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setName(nickNameTextField.getText());
                RESTRequestManager.login(new Account(Config.getId(), nickNameTextField.getText()));
                nickNameTextField.setBackground(Color.GREEN);
                nickNameTextField.setForeground(Color.BLACK);
            }
        });

        nickNamePanel.add(nickNameTextField);
        nickNamePanel.add(submitButton);

        //====================================================Graphics==========================================================
        JLabel graphicsText = new JLabel("GRAPHICS");
        graphicsText.setFont( new Font( graphicsText.getFont().getName(), Font.BOLD, 16));

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.setSize( new Dimension( getMainWidth()/3,30));
        resolutionPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        resolutionPanel.setOpaque(false);

        JLabel resolutionText = new JLabel("Resolution");
        resolutionText.setFont( new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));

        List<Resolution> spinnerList = getResolutions();
        resolutionSpinner = new JSpinner( new SpinnerListModel( spinnerList ) );
        resolutionSpinner.setValue( new Resolution(Config.getResolution().width,Config.getResolution().height));
        ((JSpinner.DefaultEditor)resolutionSpinner.getEditor()).getTextField().setColumns(6);
        ((JSpinner.DefaultEditor)resolutionSpinner.getEditor()).getTextField().setEditable(false);

        resolutionSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                resolution = (Dimension) ((JSpinner)e.getSource()).getValue();
                applied = false;
            }
        });

        resolutionPanel.add(resolutionText);
        resolutionPanel.add(resolutionSpinner);

        fullScreenCheckBox = new JCheckBox( "Full Screen", Config.isFullScreen());
        fullScreenCheckBox.setFont(new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));
        fullScreenCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fullScreen = fullScreenCheckBox.isSelected();
                applied = false;

            }
        });
        fullScreenCheckBox.setBackground(Color.WHITE);

        //====================================================Sound==========================================================
        JLabel soundText = new JLabel("SOUND");
        JLabel masterVolumeLabel = new JLabel("Master Volume");
        JLabel effectsVolumeLabel = new JLabel("Effects Volume");
        JLabel musicVolumeLabel = new JLabel("Music Volume");
        soundText.setFont( new Font( soundText.getFont().getName(), Font.BOLD, 16));

        Hashtable labels = new Hashtable();

        labels.put(0,new JLabel("LOW"));
        labels.put(100, new JLabel("HIGH"));

        UIManager.put("Slider.paintValue", true);

        masterSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getMasterSound());
        masterSlider.setLabelTable(labels);
        masterSlider.setPaintLabels(true);
        masterSlider.setBackground(Color.WHITE);
        masterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    masterSound = (int) source.getValue();
                    MainFrame.getInstance().getSoundManager().changeMusicVolume(
                            (double)((masterSlider.getValue()*musicSlider.getValue())/100));
                    MainFrame.getInstance().getSoundManager().changeEffectsVolume(
                            (double)((masterSlider.getValue()*effectsSlider.getValue())/100));
                }
            }
        });

        effectsSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getEffectsSound());
        effectsSlider.setLabelTable(labels);
        effectsSlider.setPaintLabels(true);
        effectsSlider.setBackground(Color.WHITE);
        effectsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    effectsSound = (int) source.getValue();
                    MainFrame.getInstance().getSoundManager().changeEffectsVolume(
                            (double)((masterSlider.getValue()*effectsSlider.getValue())/100));
                }
            }
        });

        musicSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getMasterSound());
        musicSlider.setLabelTable(labels);
        musicSlider.setPaintLabels(true);
        musicSlider.setBackground(Color.WHITE);
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    musicSound = (int) source.getValue();
                    MainFrame.getInstance().getSoundManager().changeMusicVolume(
                            (double)((masterSlider.getValue()*musicSlider.getValue())/100));
                }
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

                backButtonAction();
            }
        });

        CustomButton saveButton = new CustomButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                applyAction();
                SettingsScreen.super.backButtonAction();

            }
        });

        CustomButton applyButton = new CustomButton("APPLY");
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                applyAction();

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
        settingsPanel.add(masterVolumeLabel);
        settingsPanel.add(masterSlider);
        settingsPanel.add(effectsVolumeLabel);
        settingsPanel.add(effectsSlider);
        settingsPanel.add(musicVolumeLabel);
        settingsPanel.add(musicSlider);
        settingsPanel.add(buttonsPanel);
        return settingsPanel;
    }

    private int sliderAction(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();

        applied = false;

        return source.getValue();
    }

    private void applyAction(){

        if (applied)
            return;

        MainFrame.getInstance().setVisible(false);

        Config.updateConfig(resolution,fullScreen,masterSound, effectsSound, musicSound);

        if (fullScreen) {
            setFullScreen();
        }
        else {
            restoreScreen();
        }

        MainFrame.getInstance().setResolution();
        applied = true;

        MainFrame.getInstance().setVisible(true);
    }

    @Override
    protected void backButtonAction(){

        resolutionSpinner.setValue( new Resolution(Config.getResolution().width,Config.getResolution().height));
        fullScreenCheckBox.setSelected( Config.isFullScreen());
        masterSlider.setValue( Config.getMasterSound());
        effectsSlider.setValue(Config.getEffectsSound());
        musicSlider.setValue(Config.getMusicSound());
        MainFrame.getInstance().getSoundManager().initializeVolumes((double)Config.getMasterSound(),
                (double)Config.getEffectsSound(), (double)Config.getMusicSound());

        applied = true;

        super.backButtonAction();
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

    public static void setFullScreen(){

        JFrame mainFrame = MainFrame.getInstance();

        GraphicsDevice device = mainFrame.getGraphicsConfiguration().getDevice();

        if (device.isFullScreenSupported()) {

            try {
                device.setFullScreenWindow(mainFrame);
                device.setDisplayMode(new DisplayMode( Config.getResolution().width, Config.getResolution().height,
                        device.getDisplayMode().getBitDepth(), device.getDisplayMode().getRefreshRate()));
            }catch (Exception e){
                device.setFullScreenWindow(null);
            }
        }
    }

    public static void restoreScreen(){
        MainFrame.getInstance().getGraphicsConfiguration().getDevice().setFullScreenWindow(null);
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

}
