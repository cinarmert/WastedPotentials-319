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

    public boolean addCube(Cube cube, int x, int y)
    {
        grid[x][y] = cube;
        return true;
    }

    public boolean removeCube(int x, int y)
    {
        if(grid[x][y] == null)
            return false;
        grid[x][y] = null;
        return  true;
    }

    public Cube getCube(int x, int y){
        return grid[x][y];
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
        grid = new Cube[size][size];
    }

    public void setGrid(int[][] grid){
        for (int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.grid[i][j] =(grid[i][j] == -1) ? null :  new Cube(grid[i][j]);
            }
        }
    }

    public int[][] getGridIntArray()
    {
        int[][] ret = new int[size][size];

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                ret[i][j] = (grid[i][j] == null) ? -1 : grid[i][j].getCurrentFace();

        return ret;
    }
}
