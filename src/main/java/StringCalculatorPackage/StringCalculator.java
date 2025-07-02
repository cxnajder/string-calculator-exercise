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
        StringBuilder sb = new StringBuilder();
        int currentIndex = 0;
        while(currentIndex < input.length()) {
            if(Character.isDigit(input.charAt(currentIndex))){
                sb.append(input.charAt(currentIndex));
                ++currentIndex;
            }else{
                nums.add(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                int tempIndex = currentIndex;
                while(!Character.isDigit(input.charAt(currentIndex))){
                    sb.append(input.charAt(currentIndex));
                    ++currentIndex;
                }
                if (!sb.toString().equals(delimiter)) {
                    throw new Exception(String.format("'%s' expected but '%s' found at position %d.", delimiter, sb.toString(), tempIndex));
                }
                sb = new StringBuilder();
            }
        }
        nums.add(Integer.parseInt(sb.toString()));

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
