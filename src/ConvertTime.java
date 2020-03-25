import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertTime {
    private double time;

    public String convert(long time) {
        this.time = time;

        this.time = round(this.time / 60000, 2);

        if(time < 60000)
            return "now";
        else if(time < 3600000)
            return this.time + " m";
        else {
            this.time = round(this.time / 60, 2);
            return this.time + " h";
        }
    }

    private double round(double value, int decimals) {
        if (decimals < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
