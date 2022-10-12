package stringcalculator;

public class StringCalculator {

    private static String character;
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
        // step 7 also forced to change this function
        if (hasCustomSeparator(input)) {
            int longSeparatorIndex = input.indexOf("]");
            if (longSeparatorIndex == -1)
                input = input.substring(3);
            else {
                longSeparatorIndex++;
                input = input.substring(longSeparatorIndex);
            }
        }
        return input.split(separators);
    }

    private void addSeparator(String input) {
        String customSeparator = getCustomSeparator(input);
        separators += "|" + customSeparator;
    }

    private static String getCustomSeparator(String input) {
        character = String.valueOf(input.charAt(2));
        String separator = null;
        // KLDG: Coupling between split/getCustomSeparator
        // due to split logic and new feature
        // (modify this logic requires split knowledge)
        if (character.equals("[")) {
            int separatorClose = input.indexOf("]");
            separatorClose++;
            // even if we have same logic using []...
            separator = "["+ input.substring(2, separatorClose) +"]";
        } else {
            separator = character;
        }
        return separator;
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
            // also the coupling at step 7 forced me to change "" behaviour
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
