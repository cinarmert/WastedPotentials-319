package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class ChatMessage {

    @JsonProperty
    private String content;

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    public ChatMessage(){};

    public ChatMessage(String lobbyId, Account account, String content ) {
        this.content = content;
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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
