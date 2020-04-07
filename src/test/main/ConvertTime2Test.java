package main;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTime2Test {

    @Test
    void toSeconds(){
        ConvertTime2 ct2 = new ConvertTime2(5000);
        ct2.toSeconds();
        assertEquals(5, ct2.getConverted());
    }

    @Test
    void toMinutes(){
        ConvertTime2 ct2 = new ConvertTime2(150000);
        ct2.toMinutes(1);
        assertEquals(2.5, ct2.getConverted());
    }

    @Test
    void toHours(){
        ConvertTime2 ct2 = new ConvertTime2(8766000);
        ct2.toHours(1);
        assertEquals(2.4, ct2.getConverted());
    }
}