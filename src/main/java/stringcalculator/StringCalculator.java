package stringcalculator;

public class StringCalculator {

    public int add(String input) {
        String[] stringInput = input.split(",");
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
