package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.PeriodicTimeController;
import kubitz.client.storage.Lobby;

import java.util.function.Function;

public class SwitchMode extends BaseGame {

    private final int PERIOD = 15000;
    private PeriodicTimeController ptc;
    private int period;
    private Function<Void, Void> switchGrids;
    private Lobby lobby;
  
    public SwitchMode(Grid grid, Cube cube, Lobby lobby, Function<Void, Void> switchGrids) {
        super(grid, cube);
        this.lobby = lobby;
        period = PERIOD; //ToDo proper period, -consider random-
        ptc = new PeriodicTimeController(period, switchGrids);
        this.switchGrids = switchGrids;
        setCard(new Card(new Grid(4)));
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
