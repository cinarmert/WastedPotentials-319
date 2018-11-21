package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaderboardUser {

    @JsonProperty
    private int id;

    @JsonProperty
    private String name;

    @JsonProperty
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}