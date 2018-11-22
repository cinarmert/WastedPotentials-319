package kubitz.client.gui;

import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CreateLobbyScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private Dimension size;
    private JTextField nameField;
    private JComboBox<String> modeBox;
    private JComboBox noOfPlayersBox;
    private JCheckBox privateCheckBox;
    private static final Dimension BOXDIMENSION = new Dimension( 150, 30);

    public CreateLobbyScreen(JPanel contentPane, Dimension size) {

        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();

    }

    private void initializeResources() {
        setSize(size);
        setBackground( new Color(204,204,204));
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        CustomButton backButton = new CustomButton("BACK");
        backButton.addActionListener(e -> {

            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, MainFrame.LOBBIES);

        });
        c.insets = new Insets(20,20,0,0);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        this.add(backButton,c);
        c.insets = new Insets(30,30,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        this.add(initializeContainer(),c);

    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground( new Color(204,204,204));
        container.setBackground( new Color(204,204,204));
        setBorder( new LineBorder(Color.BLACK, 2));
        setPreferredSize( new Dimension( size.width-(size.width/3), size.height-(size.width/3)));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(40,0,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = size.width - (size.width/3);
        container.add(new JLabel("Create Lobby"){{
            setFont(new Font("Calibri", Font.BOLD, 26));
        }},c);
        c.gridx = 0;
        container.add(Box.createHorizontalStrut( size.width/2),c);
        c.gridx = 2;
        container.add(Box.createHorizontalStrut( size.width/2),c);
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
        container.add(Box.createVerticalStrut(size.height/6),c);


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
        String[] modelist = {Lobby.MODE_CLASSIC, Lobby.MODE_SWITCH};
        modeBox = new JComboBox(modelist);
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
        nameField.setPreferredSize(BOXDIMENSION);
        namePanel.add(nameField,BorderLayout.EAST);
        return namePanel;
    }

    private void createLobby(){
        Lobby lobby = new Lobby(nameField.getText(),(String)modeBox.getSelectedItem(),
                (int)noOfPlayersBox.getSelectedItem(), privateCheckBox.isSelected());
        lobby.addPlayer(new Account(Config.getInstance().getId(), Config.getInstance().getName()));
        MainFrame.lobbyScreen.setCurrentLobby(lobby);
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, MainFrame.LOBBY);
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
}
