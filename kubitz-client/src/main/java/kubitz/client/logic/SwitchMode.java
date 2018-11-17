package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.PeriodicTimeController;

public class SwitchMode extends BaseGame {

    PeriodicTimeController ptc;
    int period;

    public SwitchMode(Grid grid, Cube cube) {
        super(grid, cube);
        period = 150000; //ToDo proper period, -consider random-
        ptc = new PeriodicTimeController(period, this::switchGrids);
    }

    public boolean isGameFinished() {
        return false;
    }

    //return switch succeeded
    public Void switchGrids(Void v){
        return null;
    }
}
