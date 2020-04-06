package main;

import main.ConcatTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcatTimeTest {

    @Test
    void convert() {
        assertEquals("05", new ConcatTime().convert(5));
    }
}