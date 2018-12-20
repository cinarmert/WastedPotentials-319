package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class StateMessage {

    @JsonProperty
    private int[][] state;

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    public StateMessage(){};

    public StateMessage(int[][] state, Account account, String lobbyId)
    {
        this.state = state;
        this.account = account;
        this.lobbyId = lobbyId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }


    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

}
