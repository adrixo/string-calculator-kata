package stringcalculator;

import stringcalculator.exceptions.NegativesNotAllowed;
import stringcalculator.exceptions.NumberTooBig;

public class StringCalculator {

    private InputParser inputParser = new InputParser();

    public int add(String input) throws NegativesNotAllowed, NumberTooBig {
        inputParser.addCustomSeparators(input);

        String[] numbers = getNumberList(input);

        if(isEmpty(numbers))
            return 0;

        return sum(numbers);
    }

    private String[] getNumberList(String input) {
        if (InputParser.hasCustomSeparator(input)) {
            int longSeparatorIndex = input.indexOf("]");
            if (longSeparatorIndex == -1)
                input = input.substring(3);
            else {
                longSeparatorIndex++;
                input = input.substring(longSeparatorIndex);
            }
        }
        return input.split(inputParser.getSeparators());
    }

    private static boolean isEmpty(String[] stringInput) {
        return stringInput[0].equals("");
    }

    private static int sum(String[] stringInput) throws NegativesNotAllowed, NumberTooBig {
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

    private static void validateNumber(int number) throws NegativesNotAllowed, NumberTooBig {
        if (number < 0)
            throw new NegativesNotAllowed();
        if (number > 1000)
            throw new NumberTooBig();
    }

}
