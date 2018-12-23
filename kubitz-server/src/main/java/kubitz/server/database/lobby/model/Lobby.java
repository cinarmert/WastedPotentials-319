package kubitz.server.database.lobby.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kubitz.server.database.accounts.model.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "lobby")
public class Lobby {

    @Id
    private String id;

    private int maxPlayerLimit;

    private String mode;

    private boolean privateLobby;

    private String status;

    private ArrayList<Account> players;

    public Lobby(){}

    @JsonIgnore
    public int getPlayerCount() {
        return players.size();
    }

    public String getId() {
        return id;
    }

    public int getMaxPlayerLimit() {
        return maxPlayerLimit;
    }

    public String getMode() {
        return mode;
    }

    public Boolean getPrivateLobby() {
        return privateLobby;
    }

    public String getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaxPlayerLimit(int maxPlayerLimit) {
        this.maxPlayerLimit = maxPlayerLimit;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPrivateLobby() {
        return privateLobby;
    }

    public void setPrivateLobby(boolean privateLobby) {
        this.privateLobby = privateLobby;
    }

    public ArrayList<Account> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Account> players) {
        this.players = players;
    }

    public void addPlayer(Account player) {
        players.add(player);
    }

    public void removePlayer(Account player) {
        players.remove(player);
    }

    @JsonIgnore
    public boolean isFull() {
        return getPlayerCount() >= maxPlayerLimit;
    }
}

