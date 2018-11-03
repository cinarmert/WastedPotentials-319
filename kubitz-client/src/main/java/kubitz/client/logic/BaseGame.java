package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public abstract class BaseGame {

    Grid grid;
    Cube cube;
    Card card;

    public BaseGame(Grid grid, Cube cube, Card card) {
        this.grid = grid;
        this.cube = cube;
        this.card = card;
    }

    public Grid getGrid(){ return grid;}

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube cube) {
        this.cube = cube;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public abstract boolean isGameFinished();

}
