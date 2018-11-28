package kubitz.client.gui;

import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;
import kubitz.client.storage.Message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.UUID;

public class LobbyScreen extends BaseScreen {

    private static final Dimension BUTTONSIZE = new Dimension(150,20);

    private Lobby currentLobby;
    private JLabel lobbyNameLabel;
    private JList<Account> playerList;
    private DefaultListModel<Account> accountListModel;
    private Thread messageGetter;
    private List<Message> messageList;
    private JTextArea chatBox;


    public LobbyScreen(Dimension resolution) {
        super(resolution);
        initializeResources();
        messageGetter = new Thread(new MessageThread());
    }

    @Override
    public void backButtonAction(){

        super.backButtonAction();
    }

    @Override
    protected void initializeResources() {

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.anchor = GridBagConstraints.NORTH;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        this.add(initializeContainer(),c);
    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new LineBorder(Color.BLACK, 2));
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
        invitePanel.setBackground( new Color(204,204,204));
        inviteTextField = new JTextField("Enter a nickname...");

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(0,20,10,0);
        c.gridx = 0;
        c.gridy = 0;
        invitePanel.add(new JLabel("Invite a friend"),c);

        c.gridy = 1;
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

        chatBox = new JTextArea();
        chatBox.setEditable(false);
        chatBox.setBackground(new Color(153,153,153));

        JScrollPane chatScroll = new JScrollPane(chatBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScroll.setPreferredSize(new Dimension(getRightWidth()/2,100));
        chatPanel.add(chatScroll,c);

        c.gridy = 1;
        JTextField chatField = new JTextField();
        chatField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    Message message = new Message(UUID.randomUUID().toString(), currentLobby.getName(), chatField.getText(), "now", Config.getName());
                    chatField.setText("");
                    chatBox.append(getChatBoxMessage(message));
                    RESTRequestManager.postMesage(message);
                }
            }
        });
        chatField.setPreferredSize(new Dimension(getRightWidth()/2,30));
        chatPanel.add(chatField,c);

        return chatPanel;
    }

    private JPanel initializeButtons() {

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground( new Color(204,204,204));

        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(20, 20,0,0);
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
        playerList.setPreferredSize(new Dimension(getRightWidth()/2, getRightHeight()/6));

        return listPanel;
    }

    private String getChatBoxMessage(Message message){
        return message.getAuthor() + ": " + message.getMessage() + "\n";
    }

    private void kick() {

    }

    private void goSettings() {

    }

    private void startGame() {
        messageGetter.stop();

        if ( getCurrentLobby().getMode().equals(Lobby.MODE_CLASSIC) ){

            //ToDO open classic Game Screen
        }
        else if (getCurrentLobby().getMode().equals(Lobby.MODE_SWITCH)){

            //ToDo Open switch game screen
        }
    }

    private void invite(String name) {

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

        if (messageGetter.isAlive())
            messageGetter.stop();

        messageGetter = new Thread(new MessageThread());
        messageGetter.start();

    }

    private class MessageThread implements Runnable{

        @Override
        public void run() {
            while (true) {
                messageList = RESTRequestManager.getMessages(LobbyScreen.this.getCurrentLobby());
                Lobby lobby = RESTRequestManager.getLobbyByName(currentLobby.getName());
                if(lobby != null) {
                    currentLobby = lobby;
                }
                try {
                    chatBox.setText("");
                    for(Message message : messageList) {
                        chatBox.append(getChatBoxMessage(message));
                    }
                    if(lobby != null) {
                        accountListModel.removeAllElements();
                        for (Account account : currentLobby.getPlayers()) {
                            accountListModel.addElement(account);
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                    messageGetter.stop();
                }
            }
        }


    }

}
