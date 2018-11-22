package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public class ClassicMode extends BaseGame {

    public ClassicMode(Grid grid, Cube cube) {
        super(grid, cube);
    }

    @Override
    public boolean isGameFinished(){

        // ToDO check if other players finished
        return super.isGameFinished();
    }


}
