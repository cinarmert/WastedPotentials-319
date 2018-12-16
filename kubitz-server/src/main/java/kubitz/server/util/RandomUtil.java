package kubitz.server.util;

import java.util.Random;

public class RandomUtil {

    public static int[][] getRandomCard( int size)
    {
        int[][] cardGrid = new int[size][size] ;
        for( int i = 0; i < size; i++){
            for ( int j = 0; j < size; j++){
                cardGrid[i][j] = getRandomInt();
            }

        }
        return cardGrid;
    }

    public static int getRandomInt()
    {
        Random rand = new Random();
        return rand.nextInt(8);
    }
}
