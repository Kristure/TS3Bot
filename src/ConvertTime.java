import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertTime {
    private long time;

    public String convert(long time) {
        this.time = time;

        this.time = time / 1000;

        if(time < 1000)
            return "now";
        else if(time < 60000){
            this.time = (long)round(this.time, 0);
            return this.time + " s";
        }
        else if(time < 3600000) {
            int minutes = (int)round(this.time / 60, 0);
            int seconds = (int)this.time % 60;
            String minutesConverted = new ConcatTime().convert(minutes);
            String secondsConverted = new ConcatTime().convert(seconds);
            return minutesConverted + ":" + secondsConverted;
        }
        else {
            int hours = (int)round(this.time/3600, 0);
            int minutes = (int)round((this.time/60)-(hours*60),0);
            int seconds = (int)round((this.time-(hours*3600+minutes*60)),0);
            String hoursConverted = new ConcatTime().convert(hours);
            String minutesConverted = new ConcatTime().convert(minutes);
            String secondsConverted = new ConcatTime().convert(seconds);
            return hoursConverted + ":" + minutesConverted + ":" + secondsConverted;
        }
    }

    private double round(long value, int decimals) {
        if (decimals < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
