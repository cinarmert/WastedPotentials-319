package kubitz.server.database.lobby.model;

import kubitz.server.database.accounts.model.Account;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "lobby")
public class Lobby {

    @Id
    private String id;

    private String name;

    private int maxPlayerLimit;

    private String mode;

    private boolean privateLobby;

    private String status;

    private ArrayList<Account> players;

    public Lobby(){}

    public Lobby(String id, String name, int maxPlayerLimit, String mode, boolean privateLobby, String status, ArrayList<Account> players) {
        this.id = id;
        this.name = name;
        this.maxPlayerLimit = maxPlayerLimit;
        this.mode = mode;
        this.privateLobby = privateLobby;
        this.status = status;
        this.players = players;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
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

    public void setPrivateLobby(Boolean privateLobby) {
        this.privateLobby = privateLobby;
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
}
