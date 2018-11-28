package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CreateLobbyScreen extends BaseScreen{

    private JTextField nameField;
    private JComboBox<String> modeBox;
    private JComboBox noOfPlayersBox;
    private JCheckBox privateCheckBox;
    private static final Dimension BOXDIMENSION = new Dimension( 150, 30);

    public CreateLobbyScreen( Dimension resolution) {

        super(resolution);
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
        this.add(initializeContainer(),c);

    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new LineBorder(Color.BLACK, 2));
        container.setBackground( new Color(204,204,204));
        container.setBackground( new Color(204,204,204));

        container.setBorder( new LineBorder(Color.BLACK, 2));
        container.setPreferredSize( new Dimension( getRightWidth()-(getRightWidth()/3), getRightHeight()-(getRightHeight()/3)));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(40,0,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = getRightWidth() - (getRightWidth()/3);

        container.add(new JLabel("Create Lobby"){{
            setFont(new Font("Calibri", Font.BOLD, 26));
        }},c);

        c.gridx = 0;
        container.add(Box.createHorizontalStrut( getRightWidth()/2),c);

        c.gridx = 2;
        container.add(Box.createHorizontalStrut( getRightWidth()/2),c);

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
        container.add(Box.createVerticalStrut(getRightHeight()/6),c);
        return container;
    }

    private JPanel initializePrivateCheckBox() {
        JPanel privateCheckBoxPanel = new JPanel(new BorderLayout());
        privateCheckBoxPanel.setBackground( new Color(204,204,204));

        privateCheckBox = new JCheckBox("Private Lobby (Invite Only)");
        privateCheckBoxPanel.add(privateCheckBox,BorderLayout.CENTER);
        return privateCheckBoxPanel;
    }

    private JPanel initializePlayerBox() {
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBackground( new Color(204,204,204));

        Integer[] sizeList = new Integer[]{1,2,3,4};

        playerPanel.add(new JLabel("LobbySize", JLabel.LEFT), BorderLayout.WEST);

        noOfPlayersBox = new JComboBox(sizeList);
        noOfPlayersBox.setPreferredSize(BOXDIMENSION);

        playerPanel.add(noOfPlayersBox, BorderLayout.EAST);
        return playerPanel;
    }

    private JPanel initializeModeBox() {
        JPanel modePanel = new JPanel(new BorderLayout());
        modePanel.setBackground( new Color(204,204,204));
        modePanel.add(new JLabel("Game Mode", JLabel.LEFT), BorderLayout.WEST);

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

        modePanel.add(modeBox, BorderLayout.EAST);
        return modePanel;
    }

    private JPanel initializeNameField() {
        JPanel namePanel = new JPanel(new BorderLayout());
        namePanel.setBackground( new Color(204,204,204));
        namePanel.add(new JLabel("Lobby Name", JLabel.LEFT),BorderLayout.WEST);

        nameField = new JTextField("Enter name...");
        nameField.setColumns(20);
        namePanel.add(nameField,BorderLayout.EAST);
        return namePanel;
    }

    private void createLobby(){
        Lobby lobby = new Lobby(nameField.getText(),(String)modeBox.getSelectedItem(),
                (int)noOfPlayersBox.getSelectedItem(), privateCheckBox.isSelected());
        lobby.addPlayer(new Account(Config.getId(), Config.getName()));
        RESTRequestManager.createLobby(lobby);

        //ToDo make a function
        //MainFrame.lobbyScreen.setCurrentLobby(lobby);

        // ToDO open lobby screen
    }

}
