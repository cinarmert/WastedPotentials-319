package kubitz.server.websocket.storage;

import kubitz.server.database.accounts.model.Account;
import kubitz.server.database.lobby.model.Lobby;

public class ChangeSettingsMessage {

    private Lobby lobby;

    private Account account;

    public ChangeSettingsMessage(){}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Lobby getLobby(){
        return lobby;
    }

    public void setLobby(Lobby lobby){
        this.lobby = lobby;
    }
}
