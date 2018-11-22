package kubitz.client.storage;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Leaderboard {

    @JsonProperty
    private List<LeaderboardUser> leaderboard;

    public List<LeaderboardUser> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(List<LeaderboardUser> leaderboard) {
        this.leaderboard = leaderboard;
    }
}
