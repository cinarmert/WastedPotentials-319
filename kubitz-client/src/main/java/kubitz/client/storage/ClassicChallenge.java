package kubitz.client.storage;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassicChallenge {

    @JsonProperty
    private int id;

    @JsonProperty
    private int size;

    @JsonProperty
    private int[][] mission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getMission() {
        return mission;
    }

    public void setMission(int[][] mission) {
        this.mission = mission;
    }
}