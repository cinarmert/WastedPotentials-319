package kubitz.client.logic;

import kubitz.client.components.Card;
import kubitz.client.components.Cube;
import kubitz.client.components.Grid;

public abstract class BaseGame {

    Grid grid;
    Cube cube;
    Card card;

    public BaseGame(Grid grid, Cube cube) {
        this.grid = grid;
        this.cube = cube;

        // to do generate card
        this.card = new Card(new Grid(4));
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

    public boolean isGameFinished()
    {
        boolean finished = true;
        for(int i = 0; i < grid.getGrid().length; i++)
            for(int j = 0; j < grid.getGrid()[i].length; j++)
            {
                if(!finished)
                    break;
                if ( grid.getGrid()[i][j] == null || card.getGrid().getGrid()[i][j] == null) {
                    finished = false;
                    break;
                }
                finished = grid.getGrid()[i][j].getCurrentFace() == card.getGrid().getGrid()[i][j].getCurrentFace();
            }
        return finished;
    }

    public boolean moveCube(int x1, int y1, int x2, int y2)
    {
        if(grid.getGrid()[x2][y2] != null)
            return false;
        Cube cube;
        if (x1 == -1)
        {
            cube = this.cube;
            setCube(Cube.getRandomCube());
        }
        else
        {
            cube = grid.getGrid()[x1][y1];
            grid.getGrid()[x1][y1] = null;
        }

        grid.getGrid()[x2][y2] = cube;
        return true;
    }

}
