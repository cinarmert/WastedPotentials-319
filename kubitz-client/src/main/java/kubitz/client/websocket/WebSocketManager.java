package kubitz.client.websocket;

import kubitz.client.gui.Config;
import kubitz.client.storage.Lobby;
import kubitz.client.util.JsonUtil;
import kubitz.client.websocket.storage.*;
import org.java_websocket.drafts.Draft_6455;

import javax.json.Json;
import java.net.URI;
import java.net.URISyntaxException;

public class WebSocketManager {

    public static KubitzWebSocketClient client;

    public WebSocketManager(){
        try {
            client = new KubitzWebSocketClient(new URI("ws://" + Config.getServer() + "/player/" + Config.getId()), new Draft_6455());
            client.connectBlocking();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendJoinLobbyMessage(String lobbyId){
        JoinMessage joinMessage = new JoinMessage(lobbyId, Config.getAccount());
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(joinMessage));
        lm.setType(LobbyMessageTypes.LOBBY_JOIN_MESSAGE);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendLeaveLobbyMessage(String lobbyId){
        LeaveMessage leaveMessage = new LeaveMessage(lobbyId, Config.getAccount());
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(leaveMessage));
        lm.setType(LobbyMessageTypes.LOBBY_LEAVE_MESSAGE);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendStartGameMessage(String lobbyId, String gameMode){
        StartGameMessage startGameMessage = new StartGameMessage(gameMode, Config.getAccount(), lobbyId);
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(startGameMessage));
        lm.setType(LobbyMessageTypes.LOBBY_START_GAME_MSG);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendChatMessage(String autorId, String lobbyId, String content){
        ChatMessage chatMessage = new ChatMessage(lobbyId, Config.getAccount(), content);
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(chatMessage));
        lm.setType(LobbyMessageTypes.LOBBY_CHAT_MESSAGE);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendInviteMessage(String invitedPlayerId, String lobbyId){
        InviteMessage inviteMessage = new InviteMessage(invitedPlayerId, Config.getAccount(), lobbyId);
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(inviteMessage));
        lm.setType(LobbyMessageTypes.LOBBY_INVITE_MESSAGE);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendFinishGameMessage(String finishTime, String playerId, String lobbyId){
        FinishGameMessage finishMessage = new FinishGameMessage(finishTime,Config.getAccount(),lobbyId);
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(finishMessage));
        lm.setType(LobbyMessageTypes.LOBBY_FINISH_GAME);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendStateMessage(int[][] state, String playerId, String lobbyId){
        StateMessage stateMessage = new StateMessage(state, Config.getAccount(), lobbyId);
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(stateMessage));
        lm.setType(LobbyMessageTypes.LOBBY_STATE_MESSAGE);
        client.send(JsonUtil.toJson(lm));
    }

    public static void sendLobbySettingsMessage(Lobby lobby){
        ChangeSettingsMessage settingsMessage = new ChangeSettingsMessage(lobby, Config.getAccount());
        LobbyMessage lm = new LobbyMessage();
        lm.setPayload(JsonUtil.toJson(settingsMessage));
        lm.setType(LobbyMessageTypes.LOBBY_CHANGE_SETTINGS);
        client.send(JsonUtil.toJson(lm));
    }
}
