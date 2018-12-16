package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameMessage {

    @JsonProperty
    private String gameMode;

    @JsonProperty
    private String playerId;

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private int[][] card;

    public StartGameMessage(){}

    public StartGameMessage(String gameMode, String playerId, String lobbyId) {
        this.gameMode = gameMode;
        this.lobbyId = lobbyId;
        this.playerId = playerId;
    }

    public int[][] getCard() {
        return card;
    }

    public void setCard(int[][] card) {
        this.card = card;
    }

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

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
