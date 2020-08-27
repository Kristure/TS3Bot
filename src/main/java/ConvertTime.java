import java.util.concurrent.TimeUnit;

public class ConvertTime {
    public static String convert(long time) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(time),
                TimeUnit.MILLISECONDS.toMinutes(time) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(time) % TimeUnit.MINUTES.toSeconds(1));
    }

    public static Long toHours (long time) {
        return TimeUnit.MILLISECONDS.toHours(time);
    }
}
