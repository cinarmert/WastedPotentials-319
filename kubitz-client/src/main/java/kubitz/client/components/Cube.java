package kubitz.client.components;
import java.util.Random;

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
        switch (currentFace)
        {
            case RED_DOT:
                if(x == 1)
                    currentFace = RED;
                if(x == -1)
                    currentFace = WHITE;
                if(y == 1)
                    currentFace = TRIANGLE_LU;
                if(y == -1)
                    currentFace = TRIANGLE_RD;
                break;
            case WHITE_DOT:
                if(x == 1)
                    currentFace = RED;
                if(x == -1)
                    currentFace = WHITE;
                if(y == 1)
                    currentFace = TRIANGLE_RD;
                if(y == -1)
                    currentFace = TRIANGLE_LU;
                break;
            case RED:
                if(x == 1)
                    currentFace = RED_DOT;
                if(x == -1)
                    currentFace = WHITE_DOT;
                if(y == 1)
                    currentFace = TRIANGLE_LU;
                if(y == -1)
                    currentFace = TRIANGLE_RD;
                break;
            case WHITE:
                if(x == 1)
                    currentFace = RED;
                if(x == -1)
                    currentFace = WHITE;
                if(y == 1)
                    currentFace = TRIANGLE_LU;
                if(y == -1)
                    currentFace = TRIANGLE_RD;
                break;
            case TRIANGLE_LU:
                if(x == 1)
                    currentFace = RED;
                if(x == -1)
                    currentFace = WHITE;
                if(y == 1)
                    currentFace = WHITE_DOT;
                if(y == -1)
                    currentFace = RED_DOT;
                if(z == 1)
                    currentFace = TRIANGLE_RU;
                if(z == -1)
                    currentFace = TRIANGLE_LD;
                break;
            case TRIANGLE_RU:
                if(x == 1)
                    currentFace = WHITE_DOT;
                if(x == -1)
                    currentFace = RED_DOT;
                if(y == 1)
                    currentFace = RED;
                if(y == -1)
                    currentFace = WHITE;
                if(z == 1)
                    currentFace = TRIANGLE_RD;
                if(z == -1)
                    currentFace = TRIANGLE_LU;
                break;
            case TRIANGLE_LD:
                if(x == 1)
                    currentFace = WHITE;
                if(x == -1)
                    currentFace = RED;
                if(y == 1)
                    currentFace = RED_DOT;
                if(y == -1)
                    currentFace = WHITE_DOT;
                if(z == 1)
                    currentFace = TRIANGLE_LU;
                if(z == -1)
                    currentFace = TRIANGLE_RD;
                break;
            case TRIANGLE_RD:
                if(x == 1)
                    currentFace = RED_DOT;
                if(x == -1)
                    currentFace = WHITE_DOT;
                if(y == 1)
                    currentFace = WHITE;
                if(y == -1)
                    currentFace = RED;
                if(z == 1)
                    currentFace = TRIANGLE_LD;
                if(z == -1)
                    currentFace = TRIANGLE_RU;
                break;
            default: break;
        }

    }

    public static Cube getRandomCube()
    {
        Random rand = new Random();
        return new Cube(rand.nextInt(8));
    }
}
