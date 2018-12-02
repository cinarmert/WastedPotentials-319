package kubitz.client.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;

public class LobbiesFilterScreen extends BaseScreen {

    private Filter filter;
    private boolean classicMode;
    private boolean switchMode;
    private boolean showPrivate;
    private boolean showPlaying;
    private boolean showFull;

    public LobbiesFilterScreen(Dimension resolution, Filter filter) {

        super(resolution);
        this.filter = filter;
        initializeResources();
    }

    @Override
    protected void initializeResources() {

        classicMode = filter.getClassicMode();
        switchMode = filter.getSwitchMode();
        showPrivate = filter.getShowPrivate();
        showPlaying = filter.getShowPlaying();
        showFull = filter.getShowFull();

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.insets = new Insets(0,50,20,20);
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add(initializeContainer(),c);

    }

    @Override
    public void backButtonAction(){

        // ToDo get original filters

        super.backButtonAction();
    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel( new GridBagLayout());
        container.setBackground(new Color(204,204,204));
        container.setBorder(new LineBorder(Color.BLACK, 2));

        container.setPreferredSize( new Dimension( getRightWidth()-(getRightWidth()/3), getRightHeight()-(getRightHeight()/3)));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,40,0,0);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        container.add(initializeHeader(), c);

        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10, 80, 0,0);
        container.add(initializeFilterOptions(),c);

        c.gridx = 1;
        c.gridy = 0;
        container.add(Box.createHorizontalStrut( getRightWidth()/4),c);

        return container;
    }

    private JPanel initializeFilterOptions() {
        JPanel filterOptions = new JPanel();
        filterOptions.setBackground(new Color(204,204,204));
        filterOptions.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.WEST;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(30,0,0,0);
        filterOptions.add(new JLabel("Game Modes"){{
            setFont(getFont().deriveFont(getFont().getStyle() | Font.BOLD));
        }}, c);

        c.insets = new Insets(10,0,0,0);
        c.gridy = 1;

        filterOptions.add(new JCheckBox("Classic", filter.getClassicMode()){{
            addActionListener(e-> classicMode = isSelected());
        }}, c);

        c.gridy = 2;

        filterOptions.add(new JCheckBox("Switch", filter.getSwitchMode()){{
            addActionListener(e-> switchMode = isSelected());
        }}, c);

        c.gridy = 3;

        filterOptions.add(new JLabel("Other"){{
            setFont(getFont().deriveFont(getFont().getStyle() | Font.BOLD));
        }}, c);
        c.gridy = 4;

        filterOptions.add(new JCheckBox("Show private lobbies", filter.getShowPrivate()){{
            addActionListener(e-> showPrivate = isSelected());
        }}, c);
        c.gridy = 5;

        filterOptions.add(new JCheckBox("Show playing lobbies", filter.getShowPlaying()){{
            addActionListener(e-> showPlaying = isSelected());
        }}, c);
        c.gridy = 6;

        filterOptions.add(new JCheckBox("Show full lobbies", filter.getShowFull()){{
            addActionListener(e-> showFull = isSelected());
        }}, c);
        c.gridy = 7;
        c.insets = new Insets(40,0,0,0);

        filterOptions.add(new CustomButton("Apply"){{
            setPreferredSize( new Dimension(100,20));
            addActionListener(e-> setLobbyFilterAction());

            ((LobbiesScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).refresh();

        }}, c);

        c.gridy = 8;

        filterOptions.add(Box.createVerticalStrut( getRightHeight()/6),c);

        return filterOptions;
    }

    private JPanel initializeHeader() {
        JPanel header = new JPanel(new GridLayout());
        header.setBackground(new Color(204,204,204));
        JLabel filter = new JLabel("Filter");
        try {
            filter.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("/icons/filter.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        filter.setFont(header.getFont().deriveFont(32.0f));
        header.add(filter);
        return header;
    }

    private void setLobbyFilterAction(){

        filter.updateConfig(classicMode,switchMode,showPrivate,showPlaying,showFull);

        super.backButtonAction();

    }

}
