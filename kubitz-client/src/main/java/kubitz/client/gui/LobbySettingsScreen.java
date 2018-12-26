package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LobbySettingsScreen extends BaseScreen {

    private JTextField nameField;
    private JComboBox<String> modeBox;
    private JComboBox noOfPlayersBox;
    private JCheckBox privateCheckBox;
    private CustomButton saveButton;

    private Lobby currentLobby;

    private JLabel nameFieldLabel;
    private JLabel modeLabel;
    private JLabel noOfPlayersLabel;

    private final Dimension BOXDIMENSION = new Dimension( getMainWidth()/5, getMainHeight()/34);

    public LobbySettingsScreen(Dimension resolution) {
        super(resolution);
        this.requiresConnection = true;
        initializeResources();
    }

    public Lobby getCurrentLobby(){
        return currentLobby;
    }

    public void setCurrentLobby(Lobby l){

        currentLobby = l;
        nameField.setText(l.getId());
        modeBox.setSelectedItem(l.getMode());
        noOfPlayersBox.setSelectedItem(l.getMaxPlayerLimit());
        privateCheckBox.setSelected(l.isPrivate());
        if(l.getMode() == Lobby.MODE_SWITCH)
            noOfPlayersBox.setEnabled(false);

    }

    @Override
    protected void initializeResources(){

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.insets = new Insets(30,30,0,0);
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain(initializeContainer(),c);


    }

    private JPanel initializeContainer() {

        JPanel container = new JPanel( new GridBagLayout());

        container.setBackground(Theme.tablePanelColor);
        container.setBorder(new LineBorder(Color.BLACK, 3));
        container.setPreferredSize( new Dimension( getMainWidth()-(getMainWidth()/3),
                getMainHeight()-(getMainHeight()/3)));

        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(20,30,0,0);
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        JLabel header = new JLabel("Lobby Settings");
        header.setFont(container.getFont().deriveFont(32.0f));
        container.add(header,c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;

        container.add(initializeLobbySettingsContainer(),c);

        return container;
    }

    private JPanel initializeLobbySettingsContainer() {

        JPanel container = new JPanel(new GridLayout(5,1,0,20));
        container.setBackground(Theme.tablePanelColor);

        initializeNameField();

        initializeModeBox();

        initializePlayerBox();

        privateCheckBox = new JCheckBox("Private Lobby (Invite Only)");

        initializeButton();

        container.add(initializeRow(nameFieldLabel, nameField));
        container.add(initializeRow(modeLabel, modeBox));
        container.add(initializeRow(noOfPlayersLabel, noOfPlayersBox));
        container.add(initializeRow(new JLabel(""), privateCheckBox));
        container.add(initializeRow(new JLabel(""), saveButton));

        return container;
    }

    private JPanel initializeRow(JComponent component1, JComponent component2) {

        JPanel rowPanel = new JPanel(new GridLayout(1,2,0,0));
        rowPanel.setOpaque(false);

        rowPanel.add(new JPanel(new FlowLayout(FlowLayout.CENTER)){{
            add(component1);
            component2.setPreferredSize(BOXDIMENSION);
            add(component2);
            setOpaque(false);
        }});

        return rowPanel;
    }

    private void initializeButton() {
        saveButton = new CustomButton("Save");
        setPreferredSize(BOXDIMENSION);
        saveButton.addActionListener(e->saveSettings());
    }


    private void initializeModeBox() {

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

        LobbySettingsScreen.ComboBoxRenderer renderer = new LobbySettingsScreen.ComboBoxRenderer();
        modeBox.setRenderer(renderer);

        modeLabel = new JLabel("Game Mode");

    }

    private void initializePlayerBox() {

        Integer[] sizeList = new Integer[]{1,2,3,4};

        noOfPlayersBox = new JComboBox(sizeList);
        noOfPlayersBox.setPreferredSize(BOXDIMENSION);

        noOfPlayersLabel = new JLabel("Lobby Size");

    }

    private void initializeNameField() {

        nameField = new JTextField("");
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

        nameFieldLabel = new JLabel("Lobby Name");

    }

    private void saveSettings() {

        Lobby oldLobby = new Lobby(currentLobby.getId(), currentLobby.getMode(), currentLobby.getMaxPlayerLimit(), currentLobby.isPrivateLobby());

        currentLobby.setMode((String)modeBox.getSelectedItem());
        currentLobby.setId(nameField.getText());
        currentLobby.setMaxPlayerLimit((int)noOfPlayersBox.getSelectedItem());
        currentLobby.setPrivateLobby(privateCheckBox.isSelected());

        if(!currentLobby.equals(oldLobby)){
            ((LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).setCurrentLobby(currentLobby);
            WebSocketManager.sendLobbySettingsMessage(getCurrentLobby());
        }

        ScreenManager.back();

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
