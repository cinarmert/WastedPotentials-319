package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartGameMessage {

    private String gameMode;

    private String playerId;

    private String lobbyId;

    private int[][] card;

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

    public StartGameMessage(){}

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
