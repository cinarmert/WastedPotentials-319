package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StateMessage {

    private int[][] state;

    private String playerId;

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

    public StateMessage(){};

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

}
