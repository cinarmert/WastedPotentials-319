package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameState {

    @JsonProperty
    private String id;

    @JsonProperty
    private int size;

    @JsonProperty
    private int[][] state;

    @JsonProperty
    private String from;

    @JsonProperty
    private String to;

    public GameState() {}

    public GameState(String id, int size, int[][] state, String from, String to)
    {
        this.id = id;
        this.size = size;
        this.state = state;
        this.from = from;
        this.to = to;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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