package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class InputParser {

    private final ArrayList<String> separators = new ArrayList<>(Arrays.asList(",", "\n"));
    private static final int CUSTOM_SEPARATOR_INDEX = 2;
    private static final int NO_CUSTOM_SEPARATOR_INDEX = 0;

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

    private static boolean isSimpleCustomSeparator(String input) {
        return input.charAt(CUSTOM_SEPARATOR_INDEX) != '[';
    }

    private int getInstructionNumberSeparatorIndex(String input) {
        if (!hasCustomSeparator(input))
            return NO_CUSTOM_SEPARATOR_INDEX;

        if (isSimpleCustomSeparator(input)) {
            return CUSTOM_SEPARATOR_INDEX;
        }

        boolean bracketIsOpen = false;
        int complexCustomSeparatorIndex = CUSTOM_SEPARATOR_INDEX;
        for (String character : input.substring(CUSTOM_SEPARATOR_INDEX).split("")) {
            complexCustomSeparatorIndex++;
            switch (character) {
                case "[":
                    bracketIsOpen = true;
                    break;
                case "]":
                    bracketIsOpen = false;
                    break;
                case "\n":
                        return complexCustomSeparatorIndex;
                default:
                    if (!bracketIsOpen) return --complexCustomSeparatorIndex;
            }
        }

        return -1;
    }

    public void addCustomSeparators(String input) {
        if (!hasCustomSeparator(input))
            return;

        if (isSimpleCustomSeparator(input)) {
            separators.add(Character.toString(input.charAt(CUSTOM_SEPARATOR_INDEX)));
            return;
        }

        String stack = null;
        for (String character : input.substring(CUSTOM_SEPARATOR_INDEX).split("")) {
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
                   break;
               }
               stack += character;
           }
        }
    }
    
    public String[] getNumberList(String input) {
        int separatorEndIndex = getInstructionNumberSeparatorIndex(input);
        String numbers = input.substring(separatorEndIndex);
        return numbers.split(getSeparators());
    }
}
