package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.storage.Lobby;

public class ClassicMode extends BaseGame {

    Lobby lobby;

    public ClassicMode(Grid grid, Cube cube, Lobby lobby) {
        super(grid, cube);
        this.lobby = lobby;
        setCard(new Card(new Grid(4)));
    }

    @Override
    public boolean isGameFinished(){

        // ToDO check if other players finished
        return super.isGameFinished();
    }


}
