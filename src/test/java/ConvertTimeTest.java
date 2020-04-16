import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTimeTest {

    @Test
    void convertOneHour() {
        assertEquals("01:00:00", ConvertTime.convert(3600000));
    }

    @Test
    void convertOneMinute() {
        assertEquals("00:01:00", ConvertTime.convert(60000));
    }

    @Test
    void convertFiveSeconds() {
        assertEquals("00:00:05", ConvertTime.convert(5000));
    }

    @Test
    void convertMixed() {
        assertEquals("15:07:56", ConvertTime.convert(54476000));
    }

    @Test
    void oneThousandHours() {
        assertEquals(1000, ConvertTime.toHours(3600000000L));
    }
}
