package StringCalculatorPackage;

import java.util.ArrayList;

public class StringCalculator {

    static final String customDelimiterStartSign = "//";
    static final char customDelimiterEndSign = '\n';
    public static int Add(String input) throws Exception {
        if (input.isEmpty()){
            return 0;
        }
        String delimiter = ",";
        String customDelimiter = checkCustomDelimiter(input);
        if (!customDelimiter.isEmpty()) {
            delimiter = customDelimiter;
            input = removeCustomDelimiterPrefix(input);
        }
        checkDelimiterAtTheEnd(input, delimiter);
        input = replaceNewLineWithDelimiters(input, delimiter);

        int sum = 0;

        ArrayList<Integer> nums = new ArrayList<Integer>();
        ArrayList<Integer> negativeNums = new ArrayList<Integer>();
        StringBuilder sb = new StringBuilder();
        int currentIndex = 0;
        int delimiterIndex = 0;

        while(currentIndex < input.length()) {
            delimiterIndex = input.indexOf(delimiter, currentIndex);
            if (input.charAt(currentIndex) == '-' && delimiterIndex != currentIndex) {
                ++currentIndex;
                if (Character.isDigit(input.charAt(currentIndex))) {
                    // we have a negative number
                    String Num = getNum(input, currentIndex);
                    negativeNums.add(Integer.parseInt('-' + Num));
                    currentIndex += Num.length();
                } else {
                    // we have incorrect delimiter
                    int incorrectDelimiterIndex = currentIndex;
                    String incorrectDelimiter = getDelimiter(input, currentIndex);
                    currentIndex += incorrectDelimiter.length();
                }
            } else {
                // We assemby a numer
                while (currentIndex < input.length() && Character.isDigit(input.charAt(currentIndex))) {
                    sb.append(input.charAt(currentIndex));
                    ++currentIndex;
                }
                nums.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                if (currentIndex != delimiterIndex) {
                    // There is an incorrect delimiter
                    int incorrectDelimiterIndex = currentIndex;
                    String incorrectDelimiter = getDelimiter(input, currentIndex);
                    currentIndex += incorrectDelimiter.length();
                }
                else{
                    currentIndex += delimiter.length();
                }
            }
        }

        for (int n: nums){
            sum += n;
        }
        return sum;
    }

    private static String getNum(String input, int currentIndex){
        StringBuilder sb = new StringBuilder();
        while(currentIndex < input.length() && Character.isDigit(input.charAt(currentIndex))){
            sb.append(input.charAt(currentIndex));
            ++currentIndex;
        }
        return sb.toString();
    }

    private static String getDelimiter(String input, int currentIndex){
        StringBuilder sb = new StringBuilder();
        while(currentIndex < input.length() && !Character.isDigit(input.charAt(currentIndex))){
            sb.append(input.charAt(currentIndex));
            ++currentIndex;
        }
        return sb.toString();
    }

    private static void checkDelimiterAtTheEnd(String input, String delimiter) throws Exception {
        String endString = input.substring(input.length()-delimiter.length());
        if (endString.equals(delimiter))
            throw new Exception("No delimiter at the end allowed.");
    }

    private static String checkCustomDelimiter(String input){
        if (input.length() < customDelimiterStartSign.length())
            return "";
        if (!input.startsWith(customDelimiterStartSign)){
            return "";
        }
        int indexOfCustomDelimiterEnd = input.indexOf(customDelimiterEndSign);
        return input.substring(customDelimiterStartSign.length(), indexOfCustomDelimiterEnd);
    }

    private static String replaceNewLineWithDelimiters(String input, String delimeter){
       return input.replace("\n", delimeter);
    }

    private static String removeCustomDelimiterPrefix(String input){
        return input.substring(input.indexOf(customDelimiterEndSign) + 1);
    }
}
