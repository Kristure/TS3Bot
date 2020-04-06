package main;

import main.ConcatTime;
import main.ConvertTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTimeTest {

    @Test
    void convertSeconds() {
        ConvertTime convertTime = new ConvertTime();
        String convert = convertTime.convert(5000);
        assertEquals("05 s", convert);
    }

    @Test
    void convertSecondsAboveTen() {
        ConvertTime convertTime = new ConvertTime();
        String converted = convertTime.convert(15000);
        assertEquals("15 s", converted);
    }

    @Test
    void convertMinutes() {
        ConvertTime convertTime = new ConvertTime();
        String converted = convertTime.convert(5000*60);
        assertEquals("05:00", converted);
    }

    @Test
    void convertHours() {
        ConvertTime convertTime = new ConvertTime();
        String converted = convertTime.convert(5000*60*60);
        assertEquals("05:00:00", converted);
    }
}