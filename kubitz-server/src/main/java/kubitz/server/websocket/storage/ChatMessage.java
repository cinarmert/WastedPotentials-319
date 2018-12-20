package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.server.database.accounts.model.Account;

public class ChatMessage {

    private String content;

    private Account account;

    private String lobbyId;

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
    public ChatMessage(){};

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
