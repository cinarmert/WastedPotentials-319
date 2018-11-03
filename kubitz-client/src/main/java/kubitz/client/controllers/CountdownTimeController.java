package kubitz.client.controllers;

import java.util.function.Function;

/**
 * {CountdownTimeController}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class CountdownTimeController implements Runnable
{
    long remainingTime;
    Function<Void, Void> f;

    public CountdownTimeController(long remainingTime, Function<Void, Void> f)
    {
        this.remainingTime = remainingTime;
        this.f = f;
    }

    public void addRemainingTime(long time)
    {
        remainingTime += time;
    }

    public void run()
    {
        long now;
        long last = System.currentTimeMillis();
        while(remainingTime > 0)
        {
            now  = System.currentTimeMillis();
            remainingTime -= now - last;
            last = now;
        }
        f.apply(null);

    }
}
