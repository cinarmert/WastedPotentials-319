package kubitz.server.database.lobby.model;

import kubitz.server.database.accounts.model.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "lobby")
public class Lobby {

    @Id
    private String name;

    private int maxPlayerLimit;

    private String mode;

    private int playerCount;

    private boolean privateLobby;

    private String status;

    private ArrayList<Account> players;

    public Lobby(){}

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
        playerCount++;
    }

    public void removePlayer(Account player) {
        players.remove(player);
        playerCount--;
    }

    public boolean isFull() {
        return playerCount >= maxPlayerLimit;
    }
}
