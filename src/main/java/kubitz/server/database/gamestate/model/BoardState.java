package kubitz.server.database.gamestate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gamestates")
public class BoardState {

    @Id
    private long id;

    private int[][] state;

    private String from;

    private String to;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}