package main;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertTime2 {
    private Long milliseconds;
    private Double time;
    Boolean converted;

    public ConvertTime2(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void toSeconds(){
        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd.divide()
    }

    public void toSeconds(int decimals){
        double value = (double)this.milliseconds / 1000;
        round(value, decimals);
    }

    public void toMinutes(){
        double value = (double)this.milliseconds / 60000;
        this.round(value,2);
    }

    public void toMinutes(int decimals){
        double value = (double)this.milliseconds / 60000;
        this.round(value, decimals);
    }

    private double round(double value, int decimals){
        if (decimals < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(this.milliseconds);
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double getConverted() {
        return this.time;
    }


}
