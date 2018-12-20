package kubitz.client.logic;

import kubitz.client.components.Cube;
import kubitz.client.gui.Config;
import kubitz.client.storage.Lobby;
import kubitz.client.websocket.WebSocketManager;

public class ClassicMode extends BaseGame {

    Lobby lobby;

    public ClassicMode(Cube cube, Lobby lobby) {
        super(cube);
        this.lobby = lobby;
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
