package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public class ClassicMode extends BaseGame {

    public ClassicMode(Grid grid, Cube cube, Card card) {
        super(grid, cube, card);
    }

    public boolean isGameFinished() {
        return false;
    }

}
