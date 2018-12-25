package kubitz.client.rest;

import kubitz.client.storage.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RESTRequestManagerTest {

    private Account account = new Account();
    private ClassicChallenge classicChallenge = new ClassicChallenge();
    private DailyChallenges dailyChallenges = new DailyChallenges();
    private GameState gameState = new GameState();
    private Leaderboard leaderboard = new Leaderboard();
    private LeaderboardUser leaderboardUser = new LeaderboardUser();
    private Lobby lobby = new Lobby("testlobby", Lobby.MODE_CLASSIC, 5, false, Lobby.STATUS_PLAYING);
    Message message = new Message("testfromrest", "testlobby", "tstmsg", "now", "autrest");

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void login() {
        account.setId("3");
        RESTRequestManager.login(account);
    }

    @Test
    public void getClassicChallenge() {
        classicChallenge = RESTRequestManager.getClassicChallenge(3);
        assertThat(classicChallenge != null).isEqualTo(true);
        assertThat(classicChallenge.getMission() != null).isEqualTo(true);
    }

    @Test
    public void getDailyChallengeLeaderboard() {
        leaderboard = RESTRequestManager.getDailyChallengeLeaderboard();
        assertThat(leaderboard != null).isEqualTo(true);
        assertThat(leaderboard.getLeaderboard() != null).isEqualTo(true);
    }

    @Test
    public void postDailyChallengeScore() {
/*        leaderboardUser.setId("3");
        leaderboardUser.setScore(100);
        RESTRequestManager.postDailyChallengeScore(leaderboardUser);*/
    }

    @Test
    public void getDailyChallenge() {
        dailyChallenges = RESTRequestManager.getDailyChallenge();
        assertThat(dailyChallenges != null).isEqualTo(true);
        assertThat(dailyChallenges.getClassicChallenges() != null).isEqualTo(true);
    }

    @Test
    public void getSwitchOpponentGameState() {
        gameState = RESTRequestManager.getSwitchOpponentGameState("3");
        assertThat(gameState != null).isEqualTo(true);
        assertThat(gameState.getState() != null).isEqualTo(true);
    }

    @Test
    public void postSwitchGameState() {
        gameState.setTo("to");
        gameState.setFrom("from");
        int[][] a = new int[2][2];
        gameState.setState(a);
        gameState.setId("3");
        gameState.setSize(2);
        RESTRequestManager.postSwitchGameState(this.gameState);
    }

    @Test
    public void createLobby() {
        RESTRequestManager.createLobby(lobby);
    }

    @Test
    public void changeSettings() {
        lobby.setMode(Lobby.STATUS_WAITING);
        RESTRequestManager.changeSettings(lobby);
    }

    @Test
    public void getLobbies() {
        List<Lobby> response = RESTRequestManager.getLobbies();
        assertThat(response != null).isEqualTo(true);
        assertThat(lobby.getMode() != null).isEqualTo(true);
    }

    @Test
    public void kickPlayer() {
        RESTRequestManager.kickPlayer(lobby);
    }

    @Test
    public void getMessages() {
        List<Message> response = RESTRequestManager.getMessages(lobby);
        assertThat(response != null).isEqualTo(true);
    }

    @Test
    public void postMesage() {
        RESTRequestManager.postMesage(message);
    }
}