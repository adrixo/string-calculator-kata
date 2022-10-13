package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class InputParser {

    private final ArrayList<String> separators = new ArrayList<>(Arrays.asList(",", "\n"));
    private int separatorEndIndex = 0;

    public String getSeparators() {
        StringJoiner joiner = new StringJoiner("|");
        for (String sep : separators) {
            if (sep.length() > 1)
                joiner.add("[" + sep + "]");
            else
                joiner.add(sep);
        }
        return joiner.toString();
    }

    static boolean hasCustomSeparator(String input) {
        return input.startsWith("//");
    }

    private static boolean isSimpleCustomSeparator(String input, int customSeparatorIndex) {
        return input.charAt(customSeparatorIndex) != '[';
    }
    
    public void addCustomSeparators(String input) {
        if (!hasCustomSeparator(input))
            return;

        separatorEndIndex = 2; // Breaks [S]olid + temporal coupling
        if (isSimpleCustomSeparator(input, 2)) {
            separators.add(Character.toString(input.charAt(2)));
            separatorEndIndex++;
            return;
        }

        String stack = null;
        for (String character : input.substring(2).split("")) {
            separatorEndIndex++;
           if (character.equals("[")) {
               stack = "";
           }
           else if (character.equals("]")) {
               separators.add(stack);
               stack = null;
           }
           else if (character.equals("\n")) {
               break;
           }
           else {
               if (stack == null) {
                   separatorEndIndex--;
                   break;
               }
               stack += character;
           }
        }
    }
    
    public String[] getNumberList(String input) {
        // Temporal coupling, addCustomSeparators should be called first
        input = input.substring(separatorEndIndex);
        return input.split(getSeparators());
    }
}
