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
    private CustomButton createButton;

    private JLabel nameFieldLabel;
    private JLabel modeLabel;
    private JLabel noOfPlayersLabel;

    private final Dimension BOXDIMENSION = new Dimension( getMainWidth()/5, getMainHeight()/34);

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
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.addMain(initializeContainer(),c);

    }

    private JPanel initializeContainer(){

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

        JLabel header = new JLabel("Create Lobby");
        header.setFont(container.getFont().deriveFont(32.0f));
        container.add(header,c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTH;

        container.add(initializeCreateLobbyContainer(),c);

        return container;

    }

    private JPanel initializeCreateLobbyContainer() {
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
        container.add(initializeRow(new JLabel(""), createButton));

        return container;

    }

    private void initializeButton() {
        createButton = new CustomButton("Create");
        setPreferredSize(BOXDIMENSION);
        createButton.addActionListener(e->createLobby());

    }

    private JPanel initializeRow( JComponent component1, JComponent component2){
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

    private void initializePlayerBox() {

        Integer[] sizeList = new Integer[]{1,2,3,4};

        noOfPlayersBox = new JComboBox(sizeList);
        noOfPlayersBox.setPreferredSize(BOXDIMENSION);

        noOfPlayersLabel = new JLabel("Lobby Size");

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
        ComboBoxRenderer renderer = new ComboBoxRenderer();
        modeBox.setRenderer(renderer);

        modeLabel = new JLabel("Game Mode");
    }

    private void initializeNameField() {

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

        nameFieldLabel = new JLabel("Lobby Name");
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
