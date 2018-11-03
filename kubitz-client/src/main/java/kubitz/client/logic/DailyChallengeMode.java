package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public class DailyChallengeMode extends BaseGame {
    public DailyChallengeMode(Grid grid, Cube cube, Card card) {
        super(grid, cube, card);
    }

    public boolean isGameFinished() {
        return false;
    }
}
