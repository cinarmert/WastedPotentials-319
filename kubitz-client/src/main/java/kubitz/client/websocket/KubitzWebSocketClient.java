package kubitz.client.websocket;

import kubitz.client.gui.*;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.storage.*;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class KubitzWebSocketClient extends WebSocketClient {

    public KubitzWebSocketClient(URI serverUri, Draft protocolDraft) {
        super(serverUri, protocolDraft);
    }

    @Override
    public void onOpen( ServerHandshake handshake ) {
        System.out.println( "opened connection" );
    }

    @Override
    public void onMessage(String message) {
        try {
            LobbyMessage lm = JsonUtil.fromJson(message, LobbyMessage.class);

            if(lm.isChangeSettingsMessage())             handleChangeSettingsMessage(lm);
            else if(lm.isChatMessage())                  handleChatMessage(lm);
            else if(lm.isFinishGameMessage())            handleFinishGameMessage(lm);
            else if(lm.isInviteMessage())                handleInviteMessage(lm);
            else if(lm.isJoinMessage())                  handleJoinMessage(lm);
            else if(lm.isKickMessage())                  handleKickMessage(lm);
            else if(lm.isLeaveMessage())                 handleLeaveMessage(lm);
            else if(lm.isStartGameMessage())             handleStartGameMessage(lm);
            else if(lm.isStateMessage())                 handleStateMessage(lm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "closed connection: " + reason + " " +  code + " " + remote);

        if (!WebSocketManager.isReConnecting() && !(ScreenManager.getCurrentScreen() instanceof SurvivalModeScreen)) {
            if (!(ScreenManager.getCurrentScreen() instanceof MainMenuScreen)) {
                ScreenManager.reset();
            }

            ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).setCurrentLobby(null);
            JOptionPane.showMessageDialog(ScreenManager.getScreen(ScreenManager.MAIN_MENU_SCREEN),
                    "No Connection!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();
    }

    private void handleStartGameMessage(LobbyMessage lm) throws IOException {
        StartGameMessage startGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), StartGameMessage.class);
        LobbyScreen lobbyScreen = (LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN);

        if(startGameMessage.getGameMode().equals(Lobby.MODE_CLASSIC)) {
            ClassicModeScreen cms = new ClassicModeScreen(MainFrame.getResolution(), lobbyScreen.getCurrentLobby());
            cms.setCard(startGameMessage.getCard());
            ScreenManager.openGameScreen(cms);
        } else {
            SwitchModeScreen sms = new SwitchModeScreen(MainFrame.getResolution(), lobbyScreen.getCurrentLobby());
            sms.setCard(startGameMessage.getCard());
            ScreenManager.openGameScreen(sms);
        }
    }

    private void handleStateMessage(LobbyMessage lm) throws IOException {
        StateMessage stateMessage = JsonUtil.fromJson(lm.getPayload().toString(), StateMessage.class);
        SwitchModeScreen sms = (SwitchModeScreen) ScreenManager.getCurrentScreen();
        if (!Config.getId().equals(stateMessage.getAccount().getId()))
        {
            sms.setGridState(stateMessage.getState());
        }
    }

    private void handleFinishGameMessage(LobbyMessage lm) throws IOException {
        FinishGameMessage finishGameMessage = JsonUtil.fromJson(lm.getPayload().toString(), FinishGameMessage.class);

        BaseGameScreen bg = (BaseGameScreen)ScreenManager.getCurrentScreen();
        bg.getGame().setWhoWon(finishGameMessage.getAccount().getId());
        bg.onGameFinished();
    }

    private void handleLeaveMessage(LobbyMessage lm) throws IOException {
        LeaveMessage leaveMessage = JsonUtil.fromJson(lm.getPayload().toString(), LeaveMessage.class);

        LobbyScreen lobbyScreen = (LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN);
        Lobby lobby = lobbyScreen.getCurrentLobby();

        if (lobby != null) {
            lobby.getPlayers().sort(new Comparator<Account>() {
                @Override
                public int compare(Account o1, Account o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });

            lobby.removePlayer(leaveMessage.getAccount());
            if (leaveMessage.getAccount().equals(lobby.getAdmin())) {
                lobby.setAdmin(lobby.getPlayers().get(0));
            }

            lobbyScreen.setCurrentLobby(lobby);
        }
    }

    private void handleKickMessage(LobbyMessage lm) throws IOException {
        KickMessage kickMessage = JsonUtil.fromJson(lm.getPayload().toString(), KickMessage.class);
        LobbyScreen lobbyScreen = (LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN);
        Lobby lobby = lobbyScreen.getCurrentLobby();

        if (kickMessage.getAccountToKick().equals(Config.getAccount()) ){
            ScreenManager.back();
            lobbyScreen.setCurrentLobby(null);
            return;
            //ToDo cant join again
        }

        lobby.removePlayer(kickMessage.getAccountToKick());
        lobbyScreen.setCurrentLobby(lobby);
    }

    private void handleJoinMessage(LobbyMessage lm) throws IOException {
        JoinMessage joinMessage = JsonUtil.fromJson(lm.getPayload().toString(), JoinMessage.class);

        LobbyScreen lobbyScreen = (LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN);
        Lobby lobby = lobbyScreen.getCurrentLobby();
        lobby.addPlayer(joinMessage.getAccount());
        lobbyScreen.setCurrentLobby(lobby);
    }

    private void handleInviteMessage(LobbyMessage lm) throws IOException {
        InviteMessage inviteMessage = JsonUtil.fromJson(lm.getPayload().toString(), InviteMessage.class);

        if ( !( ScreenManager.getCurrentScreen() instanceof BaseGameScreen ||
                ScreenManager.getCurrentScreen() instanceof LobbyScreen ||
                ScreenManager.getCurrentScreen() instanceof  LobbySettingsScreen ||
                ScreenManager.getCurrentScreen() instanceof  CreateLobbyScreen)) {

            int invite = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
                    inviteMessage.getAccount().getId() + " invites you.",
                    "Play?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (invite == 0) {
                Lobby lobby = RESTRequestManager.getLobbyByName(inviteMessage.getLobbyId());
                WebSocketManager.sendJoinLobbyMessage(inviteMessage.getLobbyId());

                ((LobbyScreen) ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN)).setCurrentLobby(lobby);
                ScreenManager.show(ScreenManager.LOBBY_SCREEN);
            }
        }
    }

    private void handleChatMessage(LobbyMessage lm) throws IOException {
        ChatMessage chatMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChatMessage.class);
        LobbyScreen ls = ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN));

        if ( ls.getCurrentLobby() !=  null && ls.getCurrentLobby().getId().equals( chatMessage.getLobbyId() )  ) {
            ls.newMessage(chatMessage.getAccount().getId(), chatMessage.getContent());
        }

    }

    private void handleChangeSettingsMessage(LobbyMessage lm) throws IOException {
        ChangeSettingsMessage changeSettingsMessage = JsonUtil.fromJson(lm.getPayload().toString(), ChangeSettingsMessage.class);
        LobbyScreen ls = ((LobbyScreen)ScreenManager.getScreen(ScreenManager.LOBBY_SCREEN));

        ls.setCurrentLobby(changeSettingsMessage.getLobby());

    }
}
