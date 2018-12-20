package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class LeaveMessage {

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private Account account;

    public LeaveMessage(String lobbyId, Account account) {
        this.lobbyId = lobbyId;
        this.account = account;
    }

    public LeaveMessage() {}

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

}
