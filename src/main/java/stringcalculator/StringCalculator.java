package stringcalculator;

public class StringCalculator {

    public int add(String input) {
        String separators = ",|\n";
        if (input.startsWith("//")) {
            separators += "|" + input.charAt(2);
            input = input.substring(3);
        }
        String[] stringInput = input.split(separators);
        if(stringInput[0].equals("")) {
            return 0;
        }
        int result = 0;
        for(String number : stringInput) {
            result += Integer.parseInt(number);
        }
        return result;
    }

}
