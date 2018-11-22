package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaderboardUser {

    @JsonProperty
    private String id;

    @JsonProperty
    private String name;

    @JsonProperty
    private int score;

    public LeaderboardUser(){}

    public LeaderboardUser(String id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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