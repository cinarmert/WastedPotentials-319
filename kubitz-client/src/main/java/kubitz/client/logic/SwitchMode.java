package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.PeriodicTimeController;
import kubitz.client.gui.Config;
import kubitz.client.rest.RESTRequestManager;
import kubitz.client.storage.Account;
import kubitz.client.storage.GameState;
import kubitz.client.storage.Lobby;

import java.util.function.Function;

public class SwitchMode extends BaseGame {

    private final int PERIOD = 15000;
    private PeriodicTimeController ptc;
    private int period;
    private Function<Void, Void> notifySwitchGrids;
    private Lobby lobby;
  
    public SwitchMode(Cube cube, Lobby lobby, Function<Void, Void> notifySwitchGrids) {
        super(cube);
        this.lobby = lobby;
        period = PERIOD; //ToDo proper period, -consider random-
        ptc = new PeriodicTimeController(period, this::switchGrids);
        this.notifySwitchGrids = notifySwitchGrids;

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
        GameState gameState = new GameState(Config.getId(), this.grid.getSize(), this.grid.getGridIntArray(), Config.getName(), getOpponentName());
        RESTRequestManager.postSwitchGameState(gameState);
        notifySwitchGrids.apply(null);

        GameState opponent = RESTRequestManager.getSwitchOpponentGameState(getOponentID());
        this.grid.setGrid(opponent.getState());
        return null;
    }


    private String getOpponentName()
    {
        return (lobby.getPlayers().get(0).getName()).equals(Config.getName())?  lobby.getPlayers().get(1).getName() : lobby.getPlayers().get(0).getName();
    }

    private String getOponentID()
    {
        return (lobby.getPlayers().get(0).getId()).equals(Config.getId())?  lobby.getPlayers().get(1).getId() : lobby.getPlayers().get(0).getId();
    }
}
