package kubitz.client.controllers;

import java.util.function.Function;

/**
 * {PeriodicTimeController}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class PeriodicTimeController implements Runnable
{
    long period;
    boolean running = true;
    Function<Void, Void> f;

    public PeriodicTimeController(int period, Function<Void, Void> f)
    {
        this.period = period;
        this.f = f;
    }

    @Override
    public void run()
    {
        long now;
        long last = System.currentTimeMillis();

        while(running)
        {
            now  = System.currentTimeMillis();
            if(now - last > period)
            {
                last = now;
                f.apply(null);
            }
        }

    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
