package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.TimeController;

public class DailyChallengeMode extends BaseGame {

    TimeController tc;

    public DailyChallengeMode(Grid grid, Cube cube) {
        super(grid, cube);
        tc = new TimeController();
    }

    public boolean isGameFinished() {
        return false;
    }
}
