package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameState {

    @JsonProperty
    private int id;

    @JsonProperty
    private int size;

    @JsonProperty
    private int[][] state;

    @JsonProperty
    private String from;

    @JsonProperty
    private String to;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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