package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.sound.SoundManager;
import kubitz.client.storage.Account;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
    private JSpinner themeSpinner;

    private Dimension resolution;
    private boolean fullScreen;
    private int masterSound;
    private int effectsSound;
    private int musicSound;
    private String theme;

    private boolean viewChanged;
    private boolean applied;

    public SettingsScreen(Dimension resolution) {
        super(resolution);
        this.applied = true;
        this.viewChanged = false;
        initializeResources();
    }

    @Override
    protected void initializeResources(){

        this.resolution = Config.getResolution();
        this.fullScreen = Config.isFullScreen();
        this.masterSound = Config.getMasterSound();
        this.effectsSound = Config.getEffectsSound();
        this.musicSound = Config.getMusicSound();
        this.theme = Config.getTheme();

        setBackButton(true);

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHEAST;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain( initializeSettings(),c);

    }

    private JScrollPane initializeSettings() {

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(getMainWidth(), getMainHeight()));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI( new CustomScrollbarUI(scrollPane));
        scrollPane.getVerticalScrollBar().setUnitIncrement(32);

        JPanel viewPortPanel = new JPanel();
        viewPortPanel.setLayout( new GridBagLayout());
        viewPortPanel.setOpaque(false);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout( new GridLayout(16,1, 10, 5));
        settingsPanel.setOpaque(false);
        settingsPanel.setPreferredSize(new Dimension(getMainWidth()*2/3, 800 )); // height = rows * 50

        JLabel settingsText = new JLabel("SETTINGS");
        settingsText.setFont( new Font(settingsText.getFont().getName(), Font.BOLD, 34));
        settingsText.setForeground(Theme.foregroundColor);

        //====================================================Nick Name==========================================================
        JLabel nickNameText = new JLabel("NICK NAME");
        nickNameText.setFont( new Font( nickNameText.getFont().getName(), Font.BOLD, 16));
        nickNameText.setForeground(Theme.foregroundColor);

        JPanel nickNamePanel = new JPanel();
        nickNamePanel.setSize( new Dimension( getMainWidth()/3,30));
        nickNamePanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        nickNamePanel.setOpaque(false);

        nickNameTextField = new JTextField(Config.getName(),20);
        nickNameTextField.setBackground(Theme.goodColor);
        nickNameTextField.setForeground(Theme.goodTextColor);
        nickNameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyPressed(e);

                if (Config.getName().equals( nickNameTextField.getText() ) ){
                    nickNameTextField.setBackground(Theme.goodColor);
                    nickNameTextField.setForeground(Theme.goodTextColor);
                }
                else{
                    nickNameTextField.setBackground(Theme.badColor);
                    nickNameTextField.setForeground(Theme.badTextColor);
                }

            }
        });

        CustomButton submitButton = new CustomButton("SUBMIT");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setName(nickNameTextField.getText());
                RESTRequestManager.login(new Account(Config.getId(), nickNameTextField.getText()));
                nickNameTextField.setBackground(Theme.goodColor);
                nickNameTextField.setForeground(Theme.goodTextColor);
            }
        });

        nickNamePanel.add(nickNameTextField);
        nickNamePanel.add(submitButton);

        //====================================================Graphics==========================================================
        JLabel graphicsText = new JLabel("GRAPHICS");
        graphicsText.setFont( new Font( graphicsText.getFont().getName(), Font.BOLD, 16));
        graphicsText.setForeground(Theme.foregroundColor);

        JPanel resolutionPanel = new JPanel();
        resolutionPanel.setSize( new Dimension( getMainWidth()/3,30));
        resolutionPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        resolutionPanel.setOpaque(false);

        JLabel resolutionText = new JLabel("Resolution");
        resolutionText.setFont( new Font( resolutionText.getFont().getName(), Font.PLAIN, 14));
        resolutionText.setForeground(Theme.foregroundColor);

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
                viewChanged = true;
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
                viewChanged = true;

            }
        });
        fullScreenCheckBox.setForeground(Theme.foregroundColor);
        fullScreenCheckBox.setOpaque(false);
        fullScreenCheckBox.setFocusable(false);
        fullScreenCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);

        List<String> spinnerThemeList = new ArrayList<>();
        spinnerThemeList.add(ThemeManager.RED_THEME);
        spinnerThemeList.add(ThemeManager.BLUE_THEME);

        JPanel themeSpinnerPanel = new JPanel();
        themeSpinnerPanel.setSize( new Dimension( getMainWidth()/3,30));
        themeSpinnerPanel.setLayout( new FlowLayout(FlowLayout.LEFT));
        themeSpinnerPanel.setOpaque(false);

        JLabel themeText = new JLabel("Theme");
        themeText.setFont( new Font( themeText.getFont().getName(), Font.PLAIN, 14));
        themeText.setForeground(Theme.foregroundColor);

        themeSpinner = new JSpinner( new SpinnerListModel( spinnerThemeList ){
            @Override
            public Object getNextValue() {
                List list = getList();
                int index = list.indexOf(getValue());

                index = (index >= list.size() - 1) ? 0 : index + 1;
                return list.get(index);
            }

            @Override
            public Object getPreviousValue() {
                List list = getList();
                int index = list.indexOf(getValue());

                index = (index <= 0) ? list.size() - 1 : index - 1;
                return list.get(index);
            }
        } );
        themeSpinner.setValue( Config.getTheme());
        ((JSpinner.DefaultEditor)themeSpinner.getEditor()).getTextField().setColumns(6);
        ((JSpinner.DefaultEditor)themeSpinner.getEditor()).getTextField().setEditable(false);

        themeSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                theme = (String) ((JSpinner)e.getSource()).getValue();
                applied = false;
                viewChanged = true;
            }
        });

        themeSpinnerPanel.add(themeText);
        themeSpinnerPanel.add(themeSpinner);

        //====================================================Sound==========================================================
        JLabel soundText = new JLabel("SOUND");
        soundText.setFont( new Font( soundText.getFont().getName(), Font.BOLD, 16));
        soundText.setForeground(Theme.foregroundColor);

        JLabel masterVolumeLabel = new JLabel("Master Volume");
        masterVolumeLabel.setForeground(Theme.foregroundColor);

        JLabel effectsVolumeLabel = new JLabel("Effects Volume");
        effectsVolumeLabel.setForeground(Theme.foregroundColor);

        JLabel musicVolumeLabel = new JLabel("Music Volume");
        musicVolumeLabel.setForeground(Theme.foregroundColor);

        Hashtable labels = new Hashtable();

        JLabel lowLabel = new JLabel("LOW");
        lowLabel.setForeground(Theme.foregroundColor);

        JLabel highLabel = new JLabel("HIGH");
        highLabel.setForeground(Theme.foregroundColor);

        labels.put(0, lowLabel);
        labels.put(100, highLabel );

        UIManager.put("Slider.paintValue", true);

        masterSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getMasterSound());
        masterSlider.setLabelTable(labels);
        masterSlider.setPaintLabels(true);
        masterSlider.setOpaque(false);
        masterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    masterSound = sliderAction( source);
                    SoundManager.changeMusicVolume(
                            (double)((masterSound*musicSound)/100));
                    SoundManager.changeEffectsVolume(
                            (double)((masterSound*effectsSound)/100));
                }
            }
        });

        effectsSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getEffectsSound());
        effectsSlider.setLabelTable(labels);
        effectsSlider.setPaintLabels(true);
        effectsSlider.setOpaque(false);
        effectsSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    effectsSound = sliderAction(source);
                    SoundManager.changeEffectsVolume(
                            (double)((masterSound*effectsSound)/100));
                }
            }
        });

        musicSlider = new JSlider(JSlider.HORIZONTAL,
                0, 100, Config.getMusicSound());
        musicSlider.setLabelTable(labels);
        musicSlider.setPaintLabels(true);
        musicSlider.setOpaque(false);
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JSlider source = (JSlider)e.getSource();
                if (!source.getValueIsAdjusting()) {
                    musicSound = sliderAction(source);
                    SoundManager.changeMusicVolume(
                            (double)((masterSound*musicSound)/100));
                }
            }
        });


        //====================================================Buttons==========================================================

        JPanel keyBindingsPanel = new JPanel();
        keyBindingsPanel.setOpaque(false);
        keyBindingsPanel.setLayout( new FlowLayout(FlowLayout.LEFT));

        CustomButton keyBindingsButton = new CustomButton("Key Bindings");
        keyBindingsButton.addActionListener(e -> {
            ScreenManager.show(ScreenManager.KEY_BINDING_SCREEN);
        });
        keyBindingsButton.setPreferredSize( new Dimension(getMainWidth()/5,30));

        keyBindingsPanel.add(keyBindingsButton);

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
        settingsPanel.add(themeSpinnerPanel);
        settingsPanel.add(soundText);
        settingsPanel.add(masterVolumeLabel);
        settingsPanel.add(masterSlider);
        settingsPanel.add(effectsVolumeLabel);
        settingsPanel.add(effectsSlider);
        settingsPanel.add(musicVolumeLabel);
        settingsPanel.add(musicSlider);
        settingsPanel.add(keyBindingsPanel);
        settingsPanel.add(buttonsPanel);

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 1.0;

        viewPortPanel.add(settingsPanel,c);

        scrollPane.setViewportView(viewPortPanel);

        return scrollPane;
    }

    private int sliderAction(JSlider source){
        applied = false;
        return source.getValue();
    }

    private void applyAction(){

        if (applied)
            return;

        if (viewChanged){
            MainFrame.getInstance().setVisible(false);



            if (fullScreen) {
                setFullScreen();
            }
            else {
                restoreScreen();
            }

            Config.updateConfig(resolution,fullScreen,masterSound, effectsSound, musicSound);
            Config.setTheme(theme);

            ThemeManager.setTheme(theme);
            MainFrame.getInstance().setResolution();
            updateResolution(resolution);

            viewChanged = false;

            MainFrame.getInstance().setVisible(true);
        }
        else {
            Config.updateConfig(resolution,fullScreen,masterSound, effectsSound, musicSound);
        }

        applied = true;

    }

    @Override
    protected void backButtonAction(){

        resolutionSpinner.setValue( new Resolution(Config.getResolution().width,Config.getResolution().height));
        themeSpinner.setValue( Config.getTheme());
        fullScreenCheckBox.setSelected( Config.isFullScreen());
        masterSlider.setValue( Config.getMasterSound());
        effectsSlider.setValue(Config.getEffectsSound());
        musicSlider.setValue(Config.getMusicSound());
        SoundManager.initializeVolumes((double)Config.getMasterSound(),
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
