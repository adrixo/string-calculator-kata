package stringcalculator;

public class StringCalculator {

    private String separators = ",|\n";

    public int add(String input) {
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

    private static int sum(String[] stringInput) {
        int result = 0;
        for(String number : stringInput) {
            result += Integer.parseInt(number);
        }
        return result;
    }

}
