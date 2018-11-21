package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.TimeController;

import java.security.PublicKey;

public class DailyChallengeMode extends BaseGame {

    TimeController tc;
    long score = -1;

    public DailyChallengeMode(Grid grid, Cube cube) {
        super(grid, cube);
        tc = new TimeController();


        Grid cardGrid = new Grid(4);
        for( int i = 0; i < 4; i++){
            for ( int j = 0; j < 4; j++){
                cardGrid.addCube( Cube.getRandomCube(), i,j);
            }

        }
        Card card = new Card(cardGrid);

        super.setCard( card);
    }

    public void setTime(){tc.setTime();}
    public long getTimePassed(){
        return tc.getTimePassed();
    }

    public boolean isGameFinished()
    {
        boolean finsihed = super.isGameFinished();
        if(finsihed)
        {
            score = 100000000000000000l - tc.getTimePassed(); //TODO: make calculation of score meaningful
        }
        return finsihed;
    }
}
