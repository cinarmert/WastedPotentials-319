package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class CreateLobbyScreen extends BaseScreen{

    private JTextField nameField;
    private JComboBox<String> modeBox;
    private JComboBox noOfPlayersBox;
    private JCheckBox privateCheckBox;
    private static final Dimension BOXDIMENSION = new Dimension( 150, 30);

    public CreateLobbyScreen( Dimension resolution) {

        super(resolution);
        this.requiresConnection = true;
        initializeResources();
    }

    @Override
    protected void initializeResources() {

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.insets = new Insets(30,30,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain(initializeContainer(),c);

    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new LineBorder(Theme.borderColor, 2));
        container.setBackground( Theme.tablePanelColor);

        container.setBorder( new LineBorder(Theme.borderColor, 2));
        container.setPreferredSize( new Dimension( getMainWidth()-(getMainWidth()/3), getMainHeight()-(getMainHeight()/3)));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(40,0,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = getMainWidth() - (getMainWidth()/3);

        container.add(new JLabel("Create Lobby"){{
            setFont(new Font("Calibri", Font.BOLD, 26));
        }},c);

        c.gridx = 0;
        container.add(Box.createHorizontalStrut( getMainWidth()/2),c);

        c.gridx = 2;
        container.add(Box.createHorizontalStrut( getMainWidth()/2),c);

        c.gridx = 1;
        c.insets = new Insets(60,0,0,0);
        c.gridy = 1;
        container.add(initializeNameField(),c);

        c.insets = new Insets(20,0,0,0);
        c.gridy = 2;
        container.add(initializeModeBox(),c);

        c.gridy = 3;
        container.add(initializePlayerBox(),c);

        c.gridy = 4;
        container.add(initializePrivateCheckBox(), c);

        c.gridy = 5;
        c.insets = new Insets(40,0,0,0);
        container.add(new CustomButton("Create"){{
            setPreferredSize(BOXDIMENSION);
            addActionListener(e->createLobby());
        }},c);

        c.gridy = 6;
        c.insets = new Insets(20,0,0,0);
        container.add(Box.createVerticalStrut(getMainHeight()/6),c);
        return container;
    }

    private JPanel initializePrivateCheckBox() {
        JPanel privateCheckBoxPanel = new JPanel(new BorderLayout());
        privateCheckBoxPanel.setBackground( Theme.tablePanelColor);

        privateCheckBox = new JCheckBox("Private Lobby (Invite Only)");
        privateCheckBoxPanel.add(privateCheckBox,BorderLayout.CENTER);
        return privateCheckBoxPanel;
    }

    private JPanel initializePlayerBox() {
        JPanel playerPanel = new JPanel(new GridLayout(1,2));
        playerPanel.setBackground( Theme.tablePanelColor);

        Integer[] sizeList = new Integer[]{1,2,3,4};

        playerPanel.add(new JLabel("LobbySize", JLabel.LEFT));

        noOfPlayersBox = new JComboBox(sizeList);
        noOfPlayersBox.setPreferredSize(BOXDIMENSION);

        playerPanel.add(noOfPlayersBox);
        return playerPanel;
    }

    private JPanel initializeModeBox() {
        JPanel modePanel = new JPanel(new GridLayout(1,2));
        modePanel.setBackground( Theme.tablePanelColor);
        modePanel.add(new JLabel("Game Mode", JLabel.LEFT));

        String[] modeList = {Lobby.MODE_CLASSIC, Lobby.MODE_SWITCH};

        modeBox = new JComboBox(modeList);
        modeBox.setPreferredSize(BOXDIMENSION);
        modeBox.addActionListener(e->{
            if(modeBox.getSelectedItem().equals(Lobby.MODE_SWITCH)){
                noOfPlayersBox.setSelectedItem(2);
                noOfPlayersBox.setEnabled(false);
            }
            else{
                noOfPlayersBox.setSelectedItem(4);
                noOfPlayersBox.setEnabled(true);
            }

        });
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        modeBox.setRenderer(renderer);

        modePanel.add(modeBox);
        return modePanel;
    }

    private JPanel initializeNameField() {
        JPanel namePanel = new JPanel(new GridLayout(1,2));
        namePanel.setBackground( Theme.tablePanelColor);
        namePanel.add(new JLabel("Lobby Name", JLabel.LEFT));

        nameField = new JTextField("Enter name...");
        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nameField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        nameField.setColumns(20);
        nameField.setEditable(true);
        namePanel.add(nameField);
        return namePanel;
    }

    private void createLobby(){
        Lobby lobby = new Lobby(nameField.getText(),(String)modeBox.getSelectedItem(),
                (int)noOfPlayersBox.getSelectedItem(), privateCheckBox.isSelected());
        lobby.addPlayer(new Account(Config.getId()));
        RESTRequestManager.createLobby(lobby);

        ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).setCurrentLobby(lobby);

        ScreenManager.show(ScreenManager.LOBBY_SCREEN);
    }
class ComboBoxRenderer extends JLabel implements ListCellRenderer, ComboBoxEditor {

    @Override
    public Component getEditorComponent() {
        return null;
    }

    @Override
    public void setItem(Object anObject) {

    }

    @Override
    public Object getItem() {
        return null;
    }

    @Override
    public void selectAll() {

    }

    @Override
    public void addActionListener(ActionListener l) {

    }

    @Override
    public void removeActionListener(ActionListener l) {

    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        if(value.equals(Lobby.MODE_CLASSIC))
            setText("Classic");
        else
            setText("Switch");
        return this;
    }
}
}
