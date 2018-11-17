package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.TimeController;

public class DailyChallengeMode extends BaseGame {

    TimeController tc;
    long score = -1;

    public DailyChallengeMode(Grid grid, Cube cube) {
        super(grid, cube);
        tc = new TimeController();
    }

    public boolean isGameFinished()
    {
        boolean finsihed = super.isGameFinished();
        if(finsihed)
        {
            score = 100000000000000000l - tc.getTimePassed(); //TODO: make calculation of score meaningful
        }
        return finsihed;
    }
}
