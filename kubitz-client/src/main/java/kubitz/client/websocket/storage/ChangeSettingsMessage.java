package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

import java.util.ArrayList;

public class ChangeSettingsMessage {

    @JsonProperty
    private String gameMode;

    @JsonProperty
    private String lobbySize;

    @JsonProperty
    private boolean privateLobby;

    @JsonProperty
    private String playerId;

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private String status;

    @JsonProperty
    private ArrayList<Account> players;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Account> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Account> players) {
        this.players = players;
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

    public ChangeSettingsMessage(){}

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getLobbySize() {
        return lobbySize;
    }

    public void setLobbySize(String lobbySize) {
        this.lobbySize = lobbySize;
    }

    public boolean isPrivateLobby() {
        return privateLobby;
    }

    public void setPrivateLobby(boolean privateLobby) {
        this.privateLobby = privateLobby;
    }
}
