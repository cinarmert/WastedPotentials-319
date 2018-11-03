package kubitz.client.components;

/**
 * {TimeController}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class TimeController
{
    private long startTime;

    public void setTime()
    {
        startTime = System.currentTimeMillis();
    }

    public long getTimePassed()
    {
        return System.currentTimeMillis() - startTime;
    }

}
