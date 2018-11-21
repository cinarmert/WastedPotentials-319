package kubitz.client.rest;

import kubitz.client.storage.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

public class RESTRequestManagerTest {

    Account account = mock(Account.class);
    Challenge challenge = mock(Challenge.class);
    DailyChallenges dailyChallenges = mock(DailyChallenges.class);
    GameState gameState = mock(GameState.class);
    Leaderboard leaderboard = mock(Leaderboard.class);
    LeaderboardUser leaderboardUser = mock(LeaderboardUser.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void login() {
        RESTRequestManager.login(account);
    }

    @Test
    public void getClassicChallenge() {
        challenge = RESTRequestManager.getClassicChallenge(3);
        assertThat(challenge != null).isEqualTo(true);
        assertThat(challenge.getMission() != null).isEqualTo(true);
    }

    @Test
    public void getDailyChallengeLeaderboard() {
        leaderboard = RESTRequestManager.getDailyChallengeLeaderboard();
        assertThat(leaderboard != null).isEqualTo(true);
        assertThat(leaderboard.getLeaderboard() != null).isEqualTo(true);
    }

    @Test
    public void postDailyChallengeScore() {
        RESTRequestManager.postDailyChallengeScore(leaderboardUser);
    }

    @Test
    public void getDailyChallenge() {
        dailyChallenges = RESTRequestManager.getDailyChallenge();
        assertThat(dailyChallenges != null).isEqualTo(true);
        assertThat(dailyChallenges.getChallenges() != null).isEqualTo(true);
    }

    @Test
    public void getSwitchOpponentGameState() {
        gameState = RESTRequestManager.getSwitchOpponentGameState(3);
        assertThat(gameState != null).isEqualTo(true);
        assertThat(gameState.getState() != null).isEqualTo(true);
    }

    @Test
    public void postSwitchGameState() {
        RESTRequestManager.postSwitchGameState(gameState);
    }
}