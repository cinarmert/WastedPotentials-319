package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LobbyMessage {

    private String type;

    private String payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LobbyMessage(){}

    public LobbyMessage(String payload, String type){
        this.payload = payload;
        this.type = type;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }

    public int getPayloadLength() {
        return payload.length();
    }

    public boolean isLast() {
        return false;
    }

    public boolean isChangeSettingsMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_CHANGE_SETTINGS);
    }

    public boolean isChatMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_CHAT_MESSAGE);
    }

    public boolean isFinishGameMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_FINISH_GAME);
    }

    public boolean isInviteMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_INVITE_MESSAGE);
    }

    public boolean isJoinMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_JOIN_MESSAGE);
    }

    public boolean isKickMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_KICK_MESSAGE);
    }

    public boolean isLeaveMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_LEAVE_MESSAGE);
    }

    public boolean isStartGameMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_START_GAME_MSG);
    }

    public boolean isStateMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_STATE_MESSAGE);
    }

    public boolean isSuccessMessage() {
        return type.equals(LobbyMessageTypes.LOBBY_WEBSOCKET_SUCCESS_MESSAGE);
    }
}
