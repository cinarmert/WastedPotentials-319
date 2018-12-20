package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.client.storage.Account;

public class StartGameMessage {

    @JsonProperty
    private String gameMode;

    @JsonProperty
    private Account account;

    @JsonProperty
    private String lobbyId;

    @JsonProperty
    private int[][] card;

    public StartGameMessage(){}

    public StartGameMessage(String gameMode, Account account, String lobbyId) {
        this.gameMode = gameMode;
        this.lobbyId = lobbyId;
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int[][] getCard() {
        return card;
    }

    public void setCard(int[][] card) {
        this.card = card;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
