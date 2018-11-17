package kubitz.client.components;

/**
 * {Grid}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class Grid
{
    int size;
    Cube grid[][];

    public Grid(int size)
    {
        this.size = size;
        grid = new Cube[size][size];
    }

    boolean addCube(Cube cube, int x, int y)
    {
        grid[x][y] = cube;
        return true;
    }

    boolean removeCube(int x, int y)
    {
        if(grid[x][y] == null)
            return false;
        grid[x][y] = null;
        return  true;
    }

    public Cube[][] getGrid()
    {
        return grid;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
