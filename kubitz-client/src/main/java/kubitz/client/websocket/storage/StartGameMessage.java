package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameMessage {

    @JsonProperty
    private String gameMode;

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

    public StartGameMessage(){}

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
