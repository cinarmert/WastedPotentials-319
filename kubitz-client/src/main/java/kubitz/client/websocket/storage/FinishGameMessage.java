package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinishGameMessage {

    @JsonProperty
    private String finishTime;

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

    public FinishGameMessage(){}

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}