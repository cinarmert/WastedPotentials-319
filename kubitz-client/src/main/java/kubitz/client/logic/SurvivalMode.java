package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.CountdownTimeController;

import java.util.function.Function;

public class SurvivalMode extends BaseGame {

    CountdownTimeController ctc;
    long time;
    int score;
    Function<Void, Void> onGameFinished;

    public SurvivalMode(Grid grid, Cube cube, Card card, Function<Void, Void> onGameFinished) {
        super(grid, cube, card);
        time = 150000; //ToDo reasonable time period
        ctc = new CountdownTimeController(time, onGameFinished);
        this.onGameFinished = onGameFinished;
    }

    public boolean isGameFinished() {
        boolean finished = super.isGameFinished();
        if(finished)
            createNewChallenge();
        return finished;
    }

    public void createNewChallenge()
    {
        ctc.addRemainingTime(15000); //TODO reasonable time
        grid = new Grid(grid.getSize());
        card = Card.getRandomCard();
        score++; //TODO maybe change the score system??
    }

}
