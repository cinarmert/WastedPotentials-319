package kubitz.server.websocket.storage;

public class WebSocketResponseMessage {

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
