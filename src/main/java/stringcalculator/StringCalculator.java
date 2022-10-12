package stringcalculator;

public class StringCalculator {

    private String separators = ",|\n";

    public int add(String input) throws NegativesNotAllowed, NumberTooBig {
        if (hasCustomSeparator(input)) {
            addSeparator(input);
        }

        String[] numbers = getNumberList(input);

        if(isEmpty(numbers))
            return 0;

        return sum(numbers);
    }

    private String[] getNumberList(String input) {
        if (hasCustomSeparator(input)) {
            input = input.substring(3);
        }
        return input.split(separators);
    }

    private void addSeparator(String input) {
        String customSeparator = getCustomSeparator(input);
        separators += "|" + customSeparator;
    }

    private static String getCustomSeparator(String input) {
        return String.valueOf(input.charAt(2));
    }

    private static boolean hasCustomSeparator(String input) {
        return input.startsWith("//");
    }

    private static boolean isEmpty(String[] stringInput) {
        return stringInput[0].equals("");
    }

    private static int sum(String[] stringInput) throws NegativesNotAllowed, NumberTooBig {
        int result = 0;
        for(String strNumber : stringInput) {
            int number = Integer.parseInt(strNumber);
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
