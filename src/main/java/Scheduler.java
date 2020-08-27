import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Scheduler implements Runnable {
    private ScheduledExecutorService hourlyScheduler;
    private ScheduledExecutorService minuteScheduler;
    private ScheduledExecutorService oneSecScheduler;


    private void hourlyScheduler (){
        hourlyScheduler = Executors.newScheduledThreadPool(1);
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(Calendar.MINUTE);
        int minutesToNextHour = 60 - minutes;
    }

    @Override
    public void run() {

    }
}
