package stringcalculator;

import stringcalculator.exceptions.NegativesNotAllowed;
import stringcalculator.exceptions.NumberTooBig;

public class StringCalculator {

    private final InputParser inputParser = new InputParser();

    public int add(String input) {
        inputParser.addCustomSeparators(input);

        String[] numbers = inputParser.getNumberList(input);

        return sum(numbers);
    }

    private static int sum(String[] stringInput) {
        int result = 0;
        for(String strNumber : stringInput) {
            int number = 0;
            if (!strNumber.equals(""))
                number = Integer.parseInt(strNumber);
            validateNumber(number);
            result += number;
        }
        return result;
    }

    private static void validateNumber(int number) {
        if (number < 0)
            throw new NegativesNotAllowed();
        if (number > 1000)
            throw new NumberTooBig();
    }

}
