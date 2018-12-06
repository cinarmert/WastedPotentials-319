package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.server.database.accounts.model.Account;

public class JoinMessage {

    private String playerId;

    private String lobbyId;

    private Account account;

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
