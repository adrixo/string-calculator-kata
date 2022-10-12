package stringcalculator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
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
    })
    public void
    step_1_add_two_separated_numbers(String input, int expectedResult) throws NegativesNotAllowed {
        assertThat(calculator.add(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'1,2,3,4,5', 15",
            "'1,2,3,4,5,6,7,8,9', 45",
    })
    public void
    step_2_arbitrary_number_size(String input, int expectedResult) throws NegativesNotAllowed {
        assertThat(calculator.add(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'1\n2,3', 6",
            "'1,2\n3', 6",
    })
    public void
    step_3_new_line_separator(String input, int expectedResult) throws NegativesNotAllowed {
        assertThat(calculator.add(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'//;1,2,3;4', 10",
            "'//;1\n2,3;4', 10",
            "'//;1;2\n3', 6",
    })
    public void
    step_4_custom_separators(String input, int expectedResult) throws NegativesNotAllowed {
        assertThat(calculator.add(input)).isEqualTo(expectedResult);
    }

    @Test public void
    step_5_disallow_negatives() {
        assertThrows(NegativesNotAllowed.class, ()-> {
            calculator.add("1,2,-2");
        });
    }
}