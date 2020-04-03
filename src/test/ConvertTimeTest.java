import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTimeTest {

    @Test
    void convertMinutes() {
        ConvertTime convertTime = new ConvertTime();
        String convert = convertTime.convert(5000*60);
        assertEquals("05:00", convert);
    }

    @Test
    void convertHours() {
        ConvertTime convertTime = new ConvertTime();
        String convert = convertTime.convert(5000*60*60);
        assertEquals("05:00:00", convert);
    }
}