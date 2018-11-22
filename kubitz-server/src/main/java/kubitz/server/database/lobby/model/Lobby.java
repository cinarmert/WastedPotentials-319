package kubitz.server.database.lobby.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lobby")
public class Lobby {

    @Id
    private String id;

    private String name;

    private int noOfPlayers;

    private String mode;

    private boolean privateLobby;

    private String status;

    public Lobby(){}

    public Lobby(String id, String name, String mode, boolean privateLobby) {
        this.id = id;
        this.name = name;
        this.mode = mode;
        this.privateLobby = privateLobby;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
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

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
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
}
