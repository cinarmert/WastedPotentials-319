package kubitz.client.components;

public class Cube
{
    public enum Side {RED_DOT, WHITE_DOT, RED, WHITE, TRIANGLE_LU, TRIANGLE_RU, TRIANGLE_LD, TRIANGLE_RD}
    private Side state;

    public Cube(Side state)
    {
        this.state = state;
    }


    public Side getState()
    {
        return state;
    }


    public void  rotate(int x, int y, int z)
    {

    }

}
