package kubitz.client.storage;

public class Message {

    private String id;

    private String lobbyId;

    private String message;

    private String timestamp;

    private String author;

    public Message(){}

    public Message(String id, String lobbyId, String message, String timestamp, String author) {
        this.id = id;
        this.lobbyId = lobbyId;
        this.message = message;
        this.timestamp = timestamp;
        this.author = author;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}