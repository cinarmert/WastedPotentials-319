package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.PeriodicTimeController;

import java.util.function.Function;

public class SwitchMode extends BaseGame {

    PeriodicTimeController ptc;
    int period;
    Function<Void, Void> switchGrids;
  
    public SwitchMode(Grid grid, Cube cube, Function<Void, Void> switchGrids) {
        super(grid, cube);
        period = 15000; //ToDo proper period, -consider random-
        ptc = new PeriodicTimeController(period, switchGrids);
        this.switchGrids = switchGrids;
    }

    public void start(){
        ptc.start();
    }

    public void stop(){
        ptc.stop();
    }

    public SwitchMode getOtherGame(){

        return null;
    }
}
