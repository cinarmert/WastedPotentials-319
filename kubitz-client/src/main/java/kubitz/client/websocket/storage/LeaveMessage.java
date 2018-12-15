package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class LeaveMessage {

    @JsonProperty
    private String playerId;

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private Account account;

    public LeaveMessage(String playerId, String lobbyId, Account account) {
        this.playerId = playerId;
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

}
