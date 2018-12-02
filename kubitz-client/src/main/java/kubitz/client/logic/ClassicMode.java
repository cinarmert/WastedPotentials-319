package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.storage.Lobby;

public class ClassicMode extends BaseGame {

    Lobby lobby;

    public ClassicMode(Cube cube, Lobby lobby) {
        super(cube);
        this.lobby = lobby;

        //ToDo get Card from server
        setCard(new Card(new Grid(4)));
    }

    @Override
    public boolean isGameFinished(){

        boolean haveIFinished = super.isGameFinished();
        boolean hasSomebodyElseFinished = false; //TODO get this from server

        if (haveIFinished && !hasSomebodyElseFinished)
            assert true; // TODO notify server to change status to finished

        return haveIFinished || hasSomebodyElseFinished;
    }

    public String whoWon()
    {
        String ret = null;

        if(isGameFinished())
        {
            ret = "testPlayer"; //TODO get this from server
        }
        return ret;
    }


}
