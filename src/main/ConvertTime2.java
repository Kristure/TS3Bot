package main;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

public class ConvertTime2 {
    private Long milliseconds;
    private Double time;
    Boolean converted;

    public ConvertTime2(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void toSeconds(){
        /*BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();*/

        this.time = (double)TimeUnit.MILLISECONDS.toSeconds(this.milliseconds);

    }

    public void toSeconds(int decimals){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(1000), decimals, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();
    }

    public void toMinutes(){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();
    }

    public void toMinutes(int decimals){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(60000), decimals, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();
    }

    public void toHours(){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(60000*60), 2, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();
    }

    public void toHours(int decimals){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.divide(BigDecimal.valueOf(60000*60), decimals, RoundingMode.HALF_UP);
        this.time = bd.doubleValue();
    }

    public double getConverted() {
        return this.time;
    }


}
