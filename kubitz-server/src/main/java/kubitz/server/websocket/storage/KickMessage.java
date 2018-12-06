package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.server.database.accounts.model.Account;

public class KickMessage {

    private String kickedPlayerId;

    private String playerId;

    private String lobbyId;

    @JsonProperty
    private Account accountToKick;

    public Account getAccountToKick() {
        return accountToKick;
    }

    public void setAccount(Account account) {
        this.accountToKick = account;
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

    public KickMessage(){};

    public String getKickedPlayerId() {
        return kickedPlayerId;
    }

    public void setKickedPlayerId(String kickedPlayerId) {
        this.kickedPlayerId = kickedPlayerId;
    }
}
