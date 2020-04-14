package main;

import com.github.theholywaffle.teamspeak3.TS3Api;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final TS3Api api;
    private final Config config;
    private ScheduledExecutorService tenSecScheduler;
    private ScheduledExecutorService minuteScheduler;
    private ScheduledExecutorService hourlyScheduler;


    public Scheduler(TS3Api api, Config config) {
        this.api = api;
        this.config = config;
    }

    private void hourlyScheduler (){
        hourlyScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(Calendar.MINUTE);
        int minutesToHour = 60 - minutes;
        hourlyScheduler.scheduleAtFixedRate(new HourlyCheck(this.config, this.api),
                minutesToHour,
                60,
                TimeUnit.MINUTES);
    }

    private void minuteScheduler (){
        minuteScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);
        int secondsToMinute = 60 - seconds;
        minuteScheduler.scheduleAtFixedRate(new MinuteCheck(this.config, this.api),
                secondsToMinute,
                60,
                TimeUnit.SECONDS);
    }

    private void fiveSecScheduler(){
        tenSecScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        int milliSecondsToSecond = 1000 - milliseconds;
        tenSecScheduler.scheduleAtFixedRate(new FiveSecScheduler(this.config, this.api),
                milliSecondsToSecond,
                1000,
                TimeUnit.MILLISECONDS);
    }

    public void startAll(){
        hourlyScheduler();
        minuteScheduler();
        fiveSecScheduler();
        ClientIdle.updateMap(api);
    }

    public void stopAll(){
        hourlyScheduler.shutdown();
        minuteScheduler.shutdown();
        tenSecScheduler.shutdown();
    }
}
