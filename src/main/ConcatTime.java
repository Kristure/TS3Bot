package main;

public class ConcatTime {
    public String convert(int value){
        if(value < 10){
            String valueString = "0";
            valueString = valueString.concat(String.valueOf(value));
            return valueString;
        }else
            return String.valueOf(value);
    }
}
