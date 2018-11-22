package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Lobby {

    @JsonProperty
    private String name;

    @JsonProperty
    private int playerCount;

    @JsonProperty
    private String mode;

    @JsonProperty
    private String status;

    @JsonProperty
    private int maxPlayerLimit;

    @JsonProperty
    private boolean privateLobby;

    private ArrayList<Account> players;

    public final static String MODE_SWITCH = "LOBBY_MODE_SWITCH";
    public final static String MODE_CLASSIC = "LOBBY_MODE_CLASSIC";
    public final static String STATUS_WAITING = "LOBBY_STATUS_WAITING";
    public final static String STATUS_PLAYING = "LOBBY_STATUS_PLAYING";

    public Lobby() {}

    public Lobby(String name, String mode, int maxPlayerLimit, boolean privateLobby ){
        this.name = name;
        this.mode = mode;
        this.maxPlayerLimit = maxPlayerLimit;
        this.privateLobby = privateLobby;
        players = new ArrayList<>();
        playerCount = 1;
        status = Lobby.STATUS_WAITING;
    }

    public Lobby(String name, String mode, int maxPlayerLimit, boolean privateLobby, int playerCount, String status ){
        this.name = name;
        this.mode = mode;
        this.maxPlayerLimit = maxPlayerLimit;
        this.privateLobby = privateLobby;
        players = new ArrayList<>();
        this.playerCount = playerCount;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
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

    public boolean isFull(){
        return(maxPlayerLimit == playerCount);
    }

    public boolean isPlaying(){
        return !status.equals(this.STATUS_PLAYING);
    }

    public ArrayList<Account> getPlayers() {
        return players;
    }

    public void addPlayer(Account a) {
        players.add(a);
    }
}