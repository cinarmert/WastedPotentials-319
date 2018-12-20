package kubitz.server.websocket.storage;

import kubitz.server.database.accounts.model.Account;

public class LeaveMessage {

    private String lobbyId;

    private Account account;

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
