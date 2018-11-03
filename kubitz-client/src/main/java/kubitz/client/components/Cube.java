package kubitz.client.components;

public class Cube
{
    public enum Face {RED_DOT, WHITE_DOT, RED, WHITE, TRIANGLE_LU, TRIANGLE_RU, TRIANGLE_LD, TRIANGLE_RD}
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

}
