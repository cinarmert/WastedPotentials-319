package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.TimeController;
import kubitz.client.gui.Config;
import kubitz.client.gui.LeaderboardScreen;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.ClassicChallenge;
import kubitz.client.storage.DailyChallenges;

import kubitz.client.storage.LeaderboardUser;

import java.time.LocalDate;

public class DailyChallengeMode extends BaseGame {

    TimeController tc;
    int score = -1;

    //ToDo support for multiple daily challenge
    public DailyChallengeMode(Grid grid, Cube cube) {
        super(grid, cube);
        tc = new TimeController();

        DailyChallenges dailyChallenges = RESTRequestManager.getDailyChallenge();
        if (dailyChallenges == null){
            super.setCard(null);
            return;
        }
        ClassicChallenge challenge = dailyChallenges.getClassicChallenges().get(0);
        Grid cardGrid = new Grid(challenge.getSize());
        cardGrid.setGrid(challenge.getMission());
        Card card = new Card(cardGrid);
        super.setCard(card);
        super.getGrid().setSize(challenge.getSize());
    }

    public void setTime(){tc.setTime();}
    public long getTimePassed(){
        return tc.getTimePassed();
    }

    public boolean isGameFinished()
    {
        boolean finsihed = super.isGameFinished();
        if(finsihed)
        {
            Config.setLastPlayedDailyChallenge(LocalDate.now().getDayOfYear());
            score = (int) tc.getTimePassed(); //ToDo make calculation of score meaningful
            LeaderboardUser user = new LeaderboardUser(Config.getId(), Config.getName(), score);
            RESTRequestManager.postDailyChallengeScore(user);
            //ToDo open leaderboard screen
        }
        return finsihed;
    }
}
