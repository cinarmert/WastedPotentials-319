package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;
import kubitz.client.controllers.CountdownTimeController;

import java.util.function.Function;

public class SurvivalMode extends BaseGame {

    CountdownTimeController ctc;
    long time;
    int score;
    Function<Void, Void> onGameFinished;


    public SurvivalMode(Grid grid, Cube cube) {
        super(grid, cube);
        time = 150000; //ToDo reasonable time period
        ctc = new CountdownTimeController(time,  onGameFinished);
        setCard(Card.getRandomCard());
    }
  
    public SurvivalMode(Grid grid, Cube cube, Function<Void, Void> onGameFinished) {
        super(grid, cube);

        time = 150000; //ToDo reasonable time period
        ctc = new CountdownTimeController(time, onGameFinished);
        this.onGameFinished = onGameFinished;
    }

    public boolean isGameFinished() {
        return super.isGameFinished();
    }

    public void createNewChallenge()
    {
        ctc.addRemainingTime(15000); //TODO reasonable time
        grid = new Grid(grid.getSize());
        card = Card.getRandomCard();
        score++; //TODO maybe change the score system??
    }

    public void startCountDown(){
        Thread t = new Thread( ctc );
        t.start();
    }

    public long getTime(){
        return ctc.getRemainingTime();
    }

    public Void onGameFinished(Void v){
        return null;
    }

}
