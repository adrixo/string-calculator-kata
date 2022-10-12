package stringcalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorShould {

    private StringCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new StringCalculator();
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2', 3",
            "'', 0",
            "'4', 4",
            "'-1,2', 1",
            "'-1,-2', -3",
    })
    public void
    step_1_add_two_separated_numbers(String input, int expectedResult) {
        assertThat(calculator.add(input)).isEqualTo(expectedResult);
    }
}