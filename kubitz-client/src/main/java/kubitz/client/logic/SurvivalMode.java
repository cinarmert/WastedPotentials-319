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
  
    public SurvivalMode(Cube cube, Function<Void, Void> onGameFinished) {
        super(cube);

        time = 150000; //ToDo reasonable time period
        ctc = new CountdownTimeController(time, onGameFinished);
        this.onGameFinished = onGameFinished;
    }

    public boolean isGameFinished() {
        return super.isGameFinished();
    }

    public void createNewChallenge()
    {
        ctc.addRemainingTime(30000); //TODO reasonable time
        grid = new Grid(grid.getSize());
        card = Card.getRandomCard(grid.getSize());
        score++; //TODO maybe change the score system??
    }

    public void startCountDown(){
        ctc.start();
    }

    public long getTime(){
        return ctc.getRemainingTime();
    }

    public Void onGameFinished(Void v){
        return null;
    }

    public int getScore(){
        return score;
    }

    public void stopCountDown(){
        ctc.stop();
    }

}
