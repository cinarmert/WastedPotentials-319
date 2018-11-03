package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public class SurvivalMode extends BaseGame {
    public SurvivalMode(Grid grid, Cube cube, Card card) {
        super(grid, cube, card);
    }

    public boolean isGameFinished() {
        return false;
    }
}
