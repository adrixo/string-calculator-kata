package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class InputParser {

    private final ArrayList<String> separators = new ArrayList<>(Arrays.asList(",", "\n"));

    public String[] getNumberList(String input) {
        if (hasCustomSeparator(input)) {
            int longSeparatorIndex = input.indexOf("]");
            if (longSeparatorIndex == -1)
                input = input.substring(3);
            else {
                longSeparatorIndex++;
                input = input.substring(longSeparatorIndex);
            }
        }
        return input.split(getSeparators());
    }

    public String getSeparators() {
        StringJoiner joiner = new StringJoiner("|");
        for (String sep : separators) {
            joiner.add(sep);
        }
        return joiner.toString();
    }

    static boolean hasCustomSeparator(String input) {
        return input.startsWith("//");
    }

    public void addCustomSeparators(String input) {
        if (!hasCustomSeparator(input))
            return;

        String customSeparator = getCustomSeparator(input);
        separators.add(customSeparator);
    }

    private String getCustomSeparator(String input) {
        int customSeparatorIndex = 2;
        char character = input.charAt(customSeparatorIndex);

        if (input.charAt(customSeparatorIndex) != '[') {
            return String.valueOf(character);
        }

        int separatorClose = input.indexOf("]") + 1;
        return "["+ input.substring(customSeparatorIndex, separatorClose) +"]";

    }
}
