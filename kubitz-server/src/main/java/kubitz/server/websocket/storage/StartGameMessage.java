package kubitz.server.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import kubitz.server.database.accounts.model.Account;

public class StartGameMessage {

    private String gameMode;

    private Account account;

    private String lobbyId;

    private int[][] card;

    public int[][] getCard() {
        return card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public StartGameMessage(){}

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
