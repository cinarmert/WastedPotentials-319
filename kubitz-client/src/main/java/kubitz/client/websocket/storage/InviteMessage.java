package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InviteMessage {

    @JsonProperty
    private String invitedPlayerId;

    @JsonProperty
    private String playerId;

    @JsonProperty
    private String lobbyId;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public InviteMessage(){}

    public String getInvitedPlayerId() {
        return invitedPlayerId;
    }

    public void setInvitedPlayerId(String invitedPlayerId) {
        this.invitedPlayerId = invitedPlayerId;
    }
}