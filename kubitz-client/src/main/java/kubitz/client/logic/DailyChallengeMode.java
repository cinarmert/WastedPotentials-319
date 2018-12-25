package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.TimeController;
import kubitz.client.gui.Config;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.ClassicChallenge;
import kubitz.client.storage.DailyChallenges;
import kubitz.client.storage.LeaderboardUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DailyChallengeMode extends BaseGame {

    TimeController tc;
    int score = -1;
    ArrayList<Card> cards;
    int cardIndex = -1;

    public DailyChallengeMode(Cube cube) {
        super(cube);
        tc = new TimeController();

        DailyChallenges dailyChallenges = RESTRequestManager.getDailyChallenge();
        if (dailyChallenges == null){
            super.setCard(null);
            return;
        }
        cards = new ArrayList<Card>();
        for (ClassicChallenge chal : dailyChallenges.getClassicChallenges())
        {
            Grid cardGrid = new Grid(chal.getSize());
            cardGrid.setGrid(chal.getMission());
            Card card = new Card(cardGrid);
            cards.add(card);
        }
        nextCard();
    }

    private boolean nextCard()
    {
        if(cardIndex == cards.size() - 1)
            return false;
        super.signalUpdate();
        cardIndex++;
        Card card = cards.get(cardIndex);
        super.setCard(card);
        super.getGrid().setSize(card.getGrid().getSize());
        return true;
    }
    public void setTime(){tc.setTime();}
    public long getTimePassed(){
        return tc.getTimePassed();
    }

    public boolean isGameFinished()
    {
        boolean finished = super.isGameFinished();
        finished = finished && !nextCard();
        if(finished)
        {
            Config.setLastPlayedDailyChallenge(LocalDate.now().getDayOfYear());
            score = (int) tc.getTimePassed(); //ToDo make calculation of score meaningful
            LeaderboardUser user = new LeaderboardUser(Config.getAccount(), score);
            RESTRequestManager.postDailyChallengeScore(user);
            //ToDo open leaderboard screen
        }
        return finished;
    }
}
