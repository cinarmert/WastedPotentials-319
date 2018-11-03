package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.CountdownTimeController;

public class SurvivalMode extends BaseGame {

    CountdownTimeController ctc;
    long time;

    public SurvivalMode(Grid grid, Cube cube, Card card) {
        super(grid, cube, card);
        time = 150000; //ToDo reasonable time period
        ctc = new CountdownTimeController(time, this::onGameFinished);
    }

    public boolean isGameFinished() {
        return false;
    }

    public void createNewChallenge(){

    }

    public Void onGameFinished(Void v){
        return null;
    }
}
