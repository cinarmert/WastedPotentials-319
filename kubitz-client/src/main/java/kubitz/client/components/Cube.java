package kubitz.client.components;
import java.util.Random;

public class Cube
{
    public enum Face {RED_DOT, WHITE_DOT, RED, WHITE, TRIANGLE_LU, TRIANGLE_RU, TRIANGLE_LD, TRIANGLE_RD;
        public static final int size = Face.values().length;
    }
    private Face currentFace;

    public Cube(Face currentFace)
    {
        this.currentFace = currentFace;
    }


    public Face getCurrentFace()
    {
        return currentFace;
    }


    public void  rotate(int x, int y, int z)
    {

    }

    public static Cube getRandomCube()
    {
        Random rand = new Random();
        return new Cube(Face.values()[rand.nextInt(Face.size)]);
    }
}
