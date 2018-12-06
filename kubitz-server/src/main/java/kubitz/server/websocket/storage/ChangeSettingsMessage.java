package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.lobby.model.Lobby;

import java.util.ArrayList;

public class ChangeSettingsMessage {

    private String gameMode;

    private String lobbySize;

    private boolean privateLobby;

    private String playerId;

    private String lobbyId;

    private String status;

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

    public Lobby getLobby(){

        Lobby res = new Lobby();
        res.setMode(this.gameMode);
        res.setStatus(this.status);
        res.setMaxPlayerLimit(Integer.parseInt(this.lobbySize));
        res.setPrivateLobby(this.privateLobby);
        res.setPlayerCount(this.players.size());
        res.setName(this.lobbyId);
        res.setPlayers(this.players);

        return res;
    }
}
