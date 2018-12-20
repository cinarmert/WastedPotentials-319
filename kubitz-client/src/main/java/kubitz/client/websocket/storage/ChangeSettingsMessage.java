package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;
import kubitz.client.storage.Lobby;

import java.util.ArrayList;

public class ChangeSettingsMessage {

    @JsonProperty
    private Lobby lobby;

    @JsonProperty
    private Account account;

    public ChangeSettingsMessage(){}

    public Lobby getLobby() {
        return lobby;
    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
