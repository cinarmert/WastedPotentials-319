package kubitz.client.components;

public class Cube
{

    public static final int RED_DOT = 0;
    public static final int WHITE_DOT = 1;
    public static final int RED = 2;
    public static final int WHITE = 3;
    public static final int TRIANGLE_LU = 4;
    public static final int TRIANGLE_RU = 5;
    public static final int TRIANGLE_LD = 6;
    public static final int TRIANGLE_RD = 7;

    private int currentFace;

    public void setCurrentFace(int currentFace) {
        this.currentFace = currentFace;
    }

    public Cube(int currentFace)
    {
        this.currentFace = currentFace;
    }


    public int getCurrentFace()
    {
        return currentFace;
    }


    public void  rotate(int x, int y, int z)
    {

    }

}
