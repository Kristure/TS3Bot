import com.github.theholywaffle.teamspeak3.TS3Api;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private final TS3Api api;
    private ScheduledExecutorService oneSecScheduler;
    private ScheduledExecutorService minuteScheduler;
    private ScheduledExecutorService hourlyScheduler;


    public Scheduler(TS3Api api) {
        this.api = api;
    }

    private void hourlyScheduler (){
        hourlyScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(Calendar.MINUTE);
        int minutesToHour = 60 - minutes;
        hourlyScheduler.scheduleAtFixedRate(new HourlyCheck(this.api),
                minutesToHour,
                60,
                TimeUnit.MINUTES);
    }

    private void minuteScheduler (){
        minuteScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int seconds = calendar.get(Calendar.SECOND);
        int secondsToMinute = 60 - seconds;
        minuteScheduler.scheduleAtFixedRate(new MinuteCheck(this.api),
                secondsToMinute,
                60,
                TimeUnit.SECONDS);
    }

    private void oneSecScheduler(){
        oneSecScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int milliseconds = calendar.get(Calendar.MILLISECOND);
        int milliSecondsToSecond = 1000 - milliseconds;
        oneSecScheduler.scheduleAtFixedRate(new OneSecScheduler(this.api),
                milliSecondsToSecond,
                1000,
                TimeUnit.MILLISECONDS);
    }

    public void startAll(){
        hourlyScheduler();
        minuteScheduler();
        oneSecScheduler();
        ClientIdle.updateMap(api);
    }

    public void stopAll(){
        hourlyScheduler.shutdown();
        minuteScheduler.shutdown();
        oneSecScheduler.shutdown();
    }
}
