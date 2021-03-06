package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Lobby {

    @JsonProperty
    private String id;

    @JsonProperty
    private String mode;

    @JsonProperty
    private String status;

    @JsonProperty
    private int maxPlayerLimit;

    @JsonProperty
    private boolean privateLobby;

    @JsonProperty
    private ArrayList<Account> players;

    @JsonProperty
    private Account admin;

    public final static String MODE_SWITCH = "LOBBY_MODE_SWITCH";
    public final static String MODE_CLASSIC = "LOBBY_MODE_CLASSIC";
    public final static String STATUS_WAITING = "LOBBY_STATUS_WAITING";
    public final static String STATUS_PLAYING = "LOBBY_STATUS_PLAYING";

    public Lobby() {}

    public Lobby(String id, String mode, int maxPlayerLimit, boolean privateLobby, Account admin ){
        this.id = id;
        this.mode = mode;
        this.maxPlayerLimit = maxPlayerLimit;
        this.privateLobby = privateLobby;
        players = new ArrayList<>();
        status = Lobby.STATUS_WAITING;
        this.admin = admin;
    }

    public Lobby(String id, String mode, int maxPlayerLimit, boolean privateLobby, String status, Account admin ){
        this.id = id;
        this.mode = mode;
        this.maxPlayerLimit = maxPlayerLimit;
        this.privateLobby = privateLobby;
        players = new ArrayList<>();
        this.status = status;
        this.admin = admin;
    }

    public Account getAdmin() {
        return admin;
    }

    public void setAdmin(Account admin) {
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonIgnore
    public int getPlayerCount() {
        return players.size();
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMaxPlayerLimit() {
        return maxPlayerLimit;
    }

    public void setMaxPlayerLimit(int maxPlayerLimit) {
        this.maxPlayerLimit = maxPlayerLimit;
    }

    public boolean isPrivateLobby() {
        return privateLobby;
    }

    public void setPrivateLobby(boolean privateLobby) {
        this.privateLobby = privateLobby;
    }

    public boolean isPrivate() {
        return privateLobby;
    }

    @JsonIgnore
    public boolean isFull(){
        return(maxPlayerLimit == getPlayerCount());
    }

    public boolean isPlaying(){
        return !status.equals(this.STATUS_PLAYING);
    }

    public ArrayList<Account> getPlayers() {
        return players;
    }

    public void addPlayer(Account a) {
        for(Account player : players){
            if(player.getId().equals(a.getId())){
                return;
            }
        }
        players.add(a);
    }

    public void removePlayer(Account a) {
        players.remove(a);
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Lobby))
            return false;

        Lobby lobby = (Lobby)o;
        return (this.getId().equals(lobby.getId()) && this.getMode().equals(lobby.getMode()) &&
                this.getMaxPlayerLimit() == lobby.getMaxPlayerLimit() && this.isPrivate() == lobby.isPrivate());
    }
}