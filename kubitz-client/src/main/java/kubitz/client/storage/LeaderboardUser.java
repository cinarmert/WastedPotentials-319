package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaderboardUser {

    @JsonProperty
    private String id;

    @JsonProperty
    private int score;

    public LeaderboardUser(){}

    public LeaderboardUser(String id, int score) {
        this.id = id;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}