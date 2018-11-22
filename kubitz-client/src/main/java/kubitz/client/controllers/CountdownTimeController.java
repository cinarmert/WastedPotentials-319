package kubitz.client.controllers;

import java.util.function.Function;

/**
 * {CountdownTimeController}
 * Author: Yaman Yağız Taşbağ
 * Version: {3.11.2018}
 */
public class CountdownTimeController implements Runnable
{
    Long remainingTime;
    Function<Void, Void> f;
    Thread thread;

    public CountdownTimeController(long remainingTime, Function<Void, Void> f)
    {
        this.remainingTime = remainingTime;
        this.f = f;
    }

    public void addRemainingTime(long time)
    {
        stop();
        remainingTime += time;
        start();

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

        if (remainingTime <= 0)
            f.apply(null);
        stop();
    }

    public long getRemainingTime(){
        return remainingTime;}

    public void start(){
        if (thread != null)
            thread.stop();

        thread = new Thread(this);

        thread.start();

    }

    public void stop(){
        if (thread != null) {
            thread.stop();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        thread = null;
    }
}
