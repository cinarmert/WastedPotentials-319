package kubitz.client.gui;

import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LobbyScreen extends BaseScreen {

    private static final Dimension BUTTONSIZE = new Dimension(150,20);

    private Lobby currentLobby;
    private JLabel lobbyNameLabel;
    private JList<Account> playerList;
    private DefaultListModel<Account> accountListModel;
    private JTextPane chatBox;


    public LobbyScreen(Dimension resolution) {
        super(resolution);
        this.requiresConnection = true;
        initializeResources();
    }

    @Override
    protected void backButtonAction(){

        WebSocketManager.sendLeaveLobbyMessage(currentLobby.getId());
        setCurrentLobby(null);
        ScreenManager.back();
        if (ScreenManager.getCurrentScreen() instanceof CreateLobbyScreen){
            ScreenManager.back();
        }
    }

    @Override
    protected void initializeResources() {

        GridBagConstraints c = new GridBagConstraints();

        setBackButton(true);

        c.anchor = GridBagConstraints.CENTER;
        c.weighty = 1.0;
        c.gridx = 0;
        c.gridy = 0;

        this.addMain(initializeContainer(),c);
    }

    private JPanel initializeContainer() {
        JPanel container = new JPanel(new GridBagLayout());
        container.setBorder(new LineBorder(Theme.borderColor, 2));
        container.setBackground( Theme.tablePanelColor);

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
        invitePanel.setBackground( Theme.tablePanelColor);
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
        chatPanel.setBackground( Theme.tablePanelColor);

        chatBox = new JTextPane();
        chatBox.setEditable(false);
        chatBox.setBackground(Theme.backgroundColor);
        chatBox.setEditorKit( new WrapEditorKit());
        DefaultCaret caret = (DefaultCaret)chatBox.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        JScrollPane chatScroll = new JScrollPane(chatBox, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatScroll.setPreferredSize(new Dimension(getMainWidth()/2,100));
        chatScroll.getVerticalScrollBar().setUI(new CustomScrollbarUI(chatScroll));
        chatPanel.add(chatScroll,c);

        c.gridy = 1;
        JTextField chatField = new JTextField();
        chatField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
/*                    Message message = new Message(UUID.randomUUID().toString(), currentLobby.getId(), chatField.getText(), "now", Config.getId());
                    chatField.setText("");
                    chatBox.append(getChatBoxMessage(message));
                    RESTRequestManager.postMesage(message);*/
                    WebSocketManager.sendChatMessage(Config.getId(),currentLobby.getId(),chatField.getText());
                    chatField.setText("");
                }
            }
        });
        chatField.setPreferredSize(new Dimension(getMainWidth()/2,30));
        chatPanel.add(chatField,c);

        return chatPanel;
    }

    private JPanel initializeButtons() {

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground( Theme.tablePanelColor);

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

    public void newMessage(String authorId, String content){

        String author = "Player";

        for (int i = 0; i < currentLobby.getPlayerCount(); i++){
            if (currentLobby.getPlayers().get(i).getId().equals(authorId)){
                author = currentLobby.getPlayers().get(i).getId();
            }
        }

        addChatBoxMessage(author, content);
    }

    private JPanel initializePlayerList() {

        accountListModel = new DefaultListModel<>();
        JPanel listPanel = new JPanel();

        playerList = new JList<>(accountListModel);
        playerList.setFixedCellHeight(30);

        listPanel.add(playerList);
        playerList.setPreferredSize(new Dimension(getMainWidth()/2, getMainHeight()/5));

        return listPanel;
    }

    private void addChatBoxMessage(String author, String content){

        try {
            StyledDocument doc = chatBox.getStyledDocument();
            SimpleAttributeSet keyWord = new SimpleAttributeSet();
            StyleConstants.setForeground(keyWord, Theme.chatAuthorColor);
            doc.insertString(doc.getLength(), "[" + author + "] ", keyWord);

            StyleConstants.setForeground(keyWord, Theme.chatMessageColor);
            doc.insertString(doc.getLength(), content + "\n", keyWord);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

    }

    private void kick() {

    }

    private void goSettings() {

    }

    private void startGame() {
        WebSocketManager.sendStartGameMessage(getCurrentLobby().getId(), getCurrentLobby().getMode());

/*        if ( getCurrentLobby().getMode().equals(Lobby.MODE_CLASSIC) ){
            ClassicModeScreen cms = new ClassicModeScreen(MainFrame.getResolution(), getCurrentLobby());
            ScreenManager.openGameScreen(cms);
        } else if (getCurrentLobby().getMode().equals(Lobby.MODE_SWITCH)){
            SwitchModeScreen sms = new SwitchModeScreen(MainFrame.getResolution(), getCurrentLobby());
            ScreenManager.openGameScreen(sms);
        }*/
    }

    private void invite(String name) {

        //ToDo name to ID?
        WebSocketManager.sendInviteMessage(name, Config.getId(),currentLobby.getId());
    }


    public  Lobby getCurrentLobby() {
        return currentLobby;
    }

    public void setCurrentLobby(Lobby currentLobby) {

        this.currentLobby = currentLobby;

        if (currentLobby != null) {
            lobbyNameLabel.setText(currentLobby.getId());
            accountListModel.removeAllElements();

            for (int i = 0; i < currentLobby.getPlayers().size(); i++)
                accountListModel.addElement(currentLobby.getPlayers().get(i));

            playerList.setModel(accountListModel);
        }
        else {
            chatBox.setText("");
        }

    }

    //code link = https://community.oracle.com/message/10692405
    class WrapEditorKit extends StyledEditorKit {
        ViewFactory defaultFactory=new WrapColumnFactory();
        public ViewFactory getViewFactory() {
            return defaultFactory;
        }

    }

    class WrapColumnFactory implements ViewFactory {
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new WrapLabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new ParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            // default to text display
            return new LabelView(elem);
        }
    }

    class WrapLabelView extends LabelView {
        public WrapLabelView(Element elem) {
            super(elem);
        }

        public float getMinimumSpan(int axis) {
            switch (axis) {
                case View.X_AXIS:
                    return 0;
                case View.Y_AXIS:
                    return super.getMinimumSpan(axis);
                default:
                    throw new IllegalArgumentException("Invalid axis: " + axis);
            }
        }

    }

}
