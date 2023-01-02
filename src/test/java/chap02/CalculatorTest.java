package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void plus() {
        int sum = Caculator.plus(4, 1);
        assertEquals(5, sum);
    }
}
