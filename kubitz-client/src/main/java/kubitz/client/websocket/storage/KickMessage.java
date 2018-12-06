package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class KickMessage {

    @JsonProperty
    private String kickedPlayerId;

    @JsonProperty
    private String playerId;

    @JsonProperty
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
