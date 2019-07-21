package pl.sda.intermediate.tddcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    @Test
    public void shouldReturnZero() {

        // given
        String text = "";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(0, result);

    }

    @Test
    public void shouldReturnSumWhenOneGiven() {

        // given
        String text = "1";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(1, result);

    }

    @Test
    public void shouldReturnSumWhenTwoGiven() {

        // given
        String text = "1,5 ";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(6, result);

    }

    @Test
    public void shouldReturnSumWhenMoreThanTwoGiven() {

        // given
        String text = "1,5 , 4, 6, 7";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(23, result);

    }

    @Test
    public void shouldReturnSumWhenMoreThanTwoGivenWhenComaAndNewLineSeparated() {

        // given
        String text = "1,5 , 4, 6, 7 \n 4";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(27, result);

    }

    @Test
    public void shouldReturnSumWhenMoreThanTwoGivenSeparatedCustomDelimiter() {

        // given
        String text = "//;\n1 ;5 ; 4; 6; 7 ; 4; 3";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(30, result);

    }

    @Test
    public void shouldReturnSumWhenMoreThanTwoGivenSeparatedNewLineSign() {

        // given
        String text = "//;\n\n 4 \n 5 \n 5";

        // when
        int result = StringCalculator.adding(text);

        // then
        Assertions.assertEquals(14, result);

    }

}
