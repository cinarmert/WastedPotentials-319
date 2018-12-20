package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class InviteMessage {

    @JsonProperty
    private String invitedPlayerId;

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    public InviteMessage(){}

    public InviteMessage(String invitedPlayerId, Account account, String lobbyId) {
        this.invitedPlayerId = invitedPlayerId;
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

    public String getInvitedPlayerId() {
        return invitedPlayerId;
    }

    public void setInvitedPlayerId(String invitedPlayerId) {
        this.invitedPlayerId = invitedPlayerId;
    }
}
