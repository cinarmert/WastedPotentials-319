package kubitz.client.websocket.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebSocketResponseMessage {

    @JsonProperty
    private boolean successful;

    public WebSocketResponseMessage(){}

    public WebSocketResponseMessage(boolean successful) {
        this.successful = successful;
    }

    public boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
