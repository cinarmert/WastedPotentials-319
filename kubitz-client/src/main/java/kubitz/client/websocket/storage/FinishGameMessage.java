package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class FinishGameMessage {

    @JsonProperty
    private String finishTime;

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    public FinishGameMessage(){}

    public FinishGameMessage(String finishTime, Account account, String lobbyId) {
        this.finishTime = finishTime;
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

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
