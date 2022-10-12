package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class InputParser {

    private ArrayList<String> separators = new ArrayList<String>(Arrays.asList(",", "\n"));

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
