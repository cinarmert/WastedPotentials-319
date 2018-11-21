package kubitz.client.components;

/**
 * {Card}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class Card
{
    private Grid grid;

    public Card(Grid grid)
    {
        this.grid = grid;
    }
  
    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getSize(){
        return grid.getSize();
    }

    public static Card getRandomCard( int size)
    {
        Grid cardGrid = new Grid(size);
        for( int i = 0; i < size; i++){
            for ( int j = 0; j < size; j++){
                cardGrid.addCube( Cube.getRandomCube(), i,j);
            }

        }
        return new Card(cardGrid);
    }
}
