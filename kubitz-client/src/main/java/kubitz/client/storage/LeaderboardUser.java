package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LeaderboardUser {

    @JsonProperty
    private Account account;

    @JsonProperty
    private int score;

    public LeaderboardUser(){}

    public LeaderboardUser(Account account, int score) {
        this.account = account;
        this.score = score;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}