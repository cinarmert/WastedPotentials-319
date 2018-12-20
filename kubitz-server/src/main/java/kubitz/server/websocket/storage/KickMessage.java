package kubitz.server.websocket.storage;

import kubitz.server.database.accounts.model.Account;

public class KickMessage {

    private Account account;

    private String lobbyId;

    private Account accountToKick;

    public KickMessage(){};

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountToKick(Account accountToKick) {
        this.accountToKick = accountToKick;
    }

    public Account getAccountToKick() {
        return accountToKick;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

}
