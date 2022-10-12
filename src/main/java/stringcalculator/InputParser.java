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
        String character = String.valueOf(input.charAt(2));
        String separator = null;
        if (character.equals("[")) {
            int separatorClose = input.indexOf("]");
            separatorClose++;
            separator = "["+ input.substring(2, separatorClose) +"]";
        } else {
            separator = character;
        }
        return separator;
    }
}
