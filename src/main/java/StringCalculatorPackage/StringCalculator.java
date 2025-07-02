package StringCalculatorPackage;

public class StringCalculator {

    static final String customDelimiterStartSign = "//";
    static final char customDelimiterEndSign = '\n';
    public static int Add(String numbers) throws Exception {
        if (numbers.isEmpty()){
            return 0;
        }
        String delimiter = ",";
        String customDelimiter = checkCustomDelimiter(numbers);
        if (!customDelimiter.isEmpty()) {
            delimiter = customDelimiter;
            numbers = removeCustomDelimiterPrefix(numbers);
        }

        checkDelimiterAtTheEnd(numbers, delimiter);


        numbers = replaceNewLineWithDelimiters(numbers, delimiter);

        int indexOfCurrentNumber = 0;
        int indexOfNextDelimiter = numbers.indexOf(delimiter);
        int sum = 0;
        while (indexOfNextDelimiter > 0) {
            sum += getIntFromNumbers(numbers, indexOfCurrentNumber, indexOfNextDelimiter, delimiter);
            indexOfCurrentNumber = indexOfNextDelimiter + delimiter.length();
            indexOfNextDelimiter = numbers.indexOf(delimiter, indexOfNextDelimiter + delimiter.length());
        }
        sum += getIntFromNumbers(numbers, indexOfCurrentNumber, numbers.length(), delimiter);

        return sum;
    }
    private static Integer getIntFromNumbers(String numbers, int indexOfCurrentNumber, int indexOfNextDelimiter, String delimiter) throws Exception {
        int number = 0;
        try {
            number = Integer.parseInt(numbers.substring(indexOfCurrentNumber, indexOfNextDelimiter));
        } catch (NumberFormatException e) {
            for (int i = indexOfCurrentNumber; i < numbers.length(); ++i){
                if (!Character.isDigit(numbers.charAt(i)))
                    throw new Exception(String.format("'%s' expected but '%c' found at position %d.", delimiter, numbers.charAt(i), i));
            }
        }
        return number;
    }

    private static void checkDelimiterAtTheEnd(String numbers, String delimiter) throws Exception {
        String endString = numbers.substring(numbers.length()-delimiter.length());
        if (endString.equals(delimiter))
            throw new Exception("No delimiter at the end allowed.");
    }

    private static String checkCustomDelimiter(String numbers){
        if (numbers.length() < customDelimiterStartSign.length())
            return "";
        if (!numbers.startsWith(customDelimiterStartSign)){
            return "";
        }
        int indexOfCustomDelimiterEnd = numbers.indexOf(customDelimiterEndSign);
        return numbers.substring(customDelimiterStartSign.length(), indexOfCustomDelimiterEnd);
    }

    private static String replaceNewLineWithDelimiters(String numbers, String delimeter){
       return numbers.replace("\n", delimeter);
    }

    private static String removeCustomDelimiterPrefix(String numbers){
        return numbers.substring(numbers.indexOf(customDelimiterEndSign) + 1);
    }
}
