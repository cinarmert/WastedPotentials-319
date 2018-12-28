package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.PeriodicTimeController;
import kubitz.client.gui.Config;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

public class SwitchMode extends BaseGame {

    private final int PERIOD = 10000;//15000;
    private PeriodicTimeController ptc;
    private int period;
    private Lobby lobby;
  
    public SwitchMode(Cube cube, Lobby lobby) {
        super(cube);
        this.lobby = lobby;
        period = PERIOD; //ToDo proper period, -consider random-
        ptc = new PeriodicTimeController(period, this::switchGrids);


        //ToDo get Card from server
        setCard(new Card(new Grid(4)));
    }

    public void start(){
        ptc.start();
    }

    public void stop(){
        ptc.stop();
    }

    public Void switchGrids(Void v)
    {
        WebSocketManager.sendStateMessage(getGridState(), Config.getId(), lobby.getId());
        return null;
    }

    public void setGridState(int[][] state)
    {
        this.grid.setGrid(state);
    }

    public int[][] getGridState()
    {
        return this.grid.getGridIntArray();
    }

    @Override
    public boolean isGameFinished(){

        boolean haveIFinished = super.isGameFinished();

        if (haveIFinished ){
            WebSocketManager.sendFinishGameMessage("", Config.getId(), lobby.getId());
        }

        return getWhoWon() != null;
    }


}
