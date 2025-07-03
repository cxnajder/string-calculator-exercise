package StringCalculatorPackage;

import java.util.ArrayList;
import java.util.ListIterator;

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
        ArrayList<String> errors = new ArrayList<String>();

        while(currentIndex < input.length()) {
            delimiterIndex = input.indexOf(delimiter, currentIndex);
            if (input.charAt(currentIndex) == '-') {
                // we have a negative number
                sb.append(input.charAt(currentIndex));
                ++currentIndex;
                if (Character.isDigit(input.charAt(currentIndex))) {
                    while(currentIndex < input.length() && Character.isDigit(input.charAt(currentIndex))){
                        sb.append(input.charAt(currentIndex));
                        ++currentIndex;
                    }
                    negativeNums.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
            } else {
                while (currentIndex < input.length() && Character.isDigit(input.charAt(currentIndex))) {
                    sb.append(input.charAt(currentIndex));
                    ++currentIndex;
                }
                nums.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
            }
            if (currentIndex < input.length() && currentIndex != delimiterIndex) {
                // There is an incorrect delimiter
                int incorrectDelimiterIndex = currentIndex;
                while (currentIndex < input.length() && !Character.isDigit(input.charAt(currentIndex))) {
                    char c = input.charAt(currentIndex);
                    if ((c == '-') && currentIndex + 1 < input.length() && Character.isDigit(input.charAt(currentIndex + 1))) {
                        // we have a negative number next
                        break;
                    } else {
                        sb.append(c);
                        ++currentIndex;
                    }
                }
                String incorrectDelimiter = sb.toString();
                sb = new StringBuilder();
                errors.add(String.format("'%s' expected but '%s' found at position %d.", delimiter, incorrectDelimiter, incorrectDelimiterIndex));
            } else {
                currentIndex += delimiter.length();
            }
        }

        if (!negativeNums.isEmpty()){
            errors.add("Negative number(s) not allowed: " + negativeNums.toString().substring(1, negativeNums.toString().length()-1));
        }

        if (!errors.isEmpty()){
            StringBuilder errorMsg = new StringBuilder();
            ListIterator<String> errorsIt = errors.listIterator(errors.size());
            while(errorsIt.hasPrevious())
            {
                errorMsg.append(errorsIt.previous());
                if(errorsIt.hasPrevious())
                    errorMsg.append("\n");
            }
            throw new Exception(errorMsg.toString());
        }

        for (int n: nums){
            sum += n;
        }
        return sum;
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
