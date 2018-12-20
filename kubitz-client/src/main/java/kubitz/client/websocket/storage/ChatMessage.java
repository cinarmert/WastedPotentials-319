package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {

    @JsonProperty
    private String content;

    @JsonProperty
    private String playerId;

    @JsonProperty
    private String lobbyId;

    public ChatMessage(){};

    public ChatMessage(String playerId, String lobbyId, String content ) {
        this.content = content;
        this.playerId = playerId;
        this.lobbyId = lobbyId;
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


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
