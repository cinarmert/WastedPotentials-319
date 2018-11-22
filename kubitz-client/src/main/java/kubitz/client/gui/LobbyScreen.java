package kubitz.client.gui;

import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;
import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;

public class LobbyScreen extends JPanel implements Screen {

    private JPanel contentPane;
    private Lobby currentLobby;
    private Dimension size;
    private JLabel lobbyNameLabel;
    private JList<Account> playerList;
    private DefaultListModel<Account> accountListModel;
    private static final Dimension BUTTONSIZE = new Dimension(150,20);


    public LobbyScreen(JPanel contentPane, Dimension size) {
        super();
        this.contentPane = contentPane;
        this.size = size;
        initializeResources();
    }

    private void initializeResources() {

        setSize(size);
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
        c.insets = new Insets(0,0,0,0);
        c.anchor = GridBagConstraints.NORTH;
        c.gridx = 0;
        c.gridy = 1;
        this.add(initializeContainer(),c);
    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBackground( new Color(204,204,204));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets( 30, 30, 30,30);
        c.anchor = GridBagConstraints.NORTHWEST;
        c.gridy = 0;
        c.gridx = 0;
        lobbyNameLabel = new JLabel("Lobby");
        lobbyNameLabel.setFont(new Font("Calibri", Font.BOLD, 26));
        container.add(lobbyNameLabel,c);
        c.gridy = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        container.add(initializePlayerList(), c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        container.add(initializeButtons(),c);
        c.gridx = 0;
        c.gridy = 2;
        c.anchor = GridBagConstraints.NORTHWEST;
        container.add(initializeChat(), c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        container.add(initializeInvite(), c);
        return container;
    }

    private JPanel initializeInvite() {
        JPanel invitePanel = new JPanel( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JTextField inviteTextField;
        c.anchor = GridBagConstraints.CENTER;
        invitePanel.setBackground( new Color(204,204,204));
        c.insets = new Insets(0,20,10,0);
        c.gridx = 0;
        c.gridy = 0;
        invitePanel.add(new JLabel("Invite a friend"),c);
        c.gridy = 1;
        inviteTextField = new JTextField("Enter a nickname...");
        invitePanel.add(inviteTextField,c);
        c.gridy = 2;
        invitePanel.add(new CustomButton("Invite"){{
            setPreferredSize(BUTTONSIZE);
            addActionListener(e->invite(inviteTextField.getText()));
        }},c);



        return invitePanel;
    }



    private JPanel initializeChat() {
        JPanel chatPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        chatPanel.setBackground( new Color(204,204,204));
        JTextArea chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setText("Player 1: Hello Player2!\nPlayer 2: Hello!");
        chatBox.setBackground(new Color(153,153,153));
        chatBox.setPreferredSize(new Dimension(size.width/2,100));
        JScrollPane chatScroll = new JScrollPane(chatBox);
        chatPanel.add(chatScroll,c);
        c.gridy = 1;
        JTextField chatField = new JTextField("Enter message...");
        chatField.setPreferredSize(new Dimension(size.width/2,30));
        chatPanel.add(chatField,c);
        return chatPanel;
    }

    private JPanel initializeButtons() {

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 20,0,0);
        buttonPanel.setBackground( new Color(204,204,204));
        buttonPanel.add(new CustomButton("Start"){{
            setPreferredSize(BUTTONSIZE);
            addActionListener(e->startGame());
        }},c);

        c.gridy = 1;

        buttonPanel.add(new CustomButton("Settings"){{
            setPreferredSize(BUTTONSIZE);
            addActionListener(e->goSettings());
        }},c);

        c.gridy = 2;

        buttonPanel.add(new CustomButton("Kick player"){{
            setPreferredSize(BUTTONSIZE);
            addActionListener(e->kick());
        }},c);

        return buttonPanel;
    }


    private JPanel initializePlayerList() {

        accountListModel = new DefaultListModel<>();
        JPanel listPanel = new JPanel();
        playerList = new JList<>(accountListModel);
        playerList.setFixedCellHeight(30);
        listPanel.add(playerList);
        playerList.setPreferredSize(new Dimension(size.width/2,size.height/6));

        return listPanel;
    }

    private void kick() {

    }

    private void goSettings() {

    }

    private void startGame() {

    }

    private void invite(String name) {

    }

    @Override
    public void update() {

    }

    @Override
    public void updateResolution(Dimension size) {

    }

    public  Lobby getCurrentLobby() {
        return currentLobby;
    }

    public void setCurrentLobby(Lobby currentLobby) {


        this.currentLobby = currentLobby;
        lobbyNameLabel.setText(currentLobby.getName());
        for(int i = 0; i < currentLobby.getPlayers().size(); i++ )
            accountListModel.addElement(currentLobby.getPlayers().get(i));
        playerList.setModel(accountListModel);


    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( MainFrame.background, 0, 0, getWidth(), getHeight(), this);
    }

}
