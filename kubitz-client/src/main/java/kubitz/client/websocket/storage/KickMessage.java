package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class KickMessage {

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private Account accountToKick;

    public KickMessage(){};

    public KickMessage(Account account, String lobbyId, Account accountToKick) {
        this.account = account;
        this.lobbyId = lobbyId;
        this.accountToKick = accountToKick;
    }

    public Account getAccount() {
        return account;
    }

    public Account getAccountToKick() {
        return accountToKick;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountToKick(Account account) {
        this.accountToKick = accountToKick;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

}
