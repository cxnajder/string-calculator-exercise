package StringCalculatorPackage;

public class StringCalculator {

    static final String customDelimiterStartSign = "//";
    static final char customDelimiterEndSign = '\n';
    public static int Add(String numbers) throws Exception {
        if (numbers.isEmpty()){
            return 0;
        }
        String delimiter = ",";

        String customCustomDelimiter = checkCustomCustomDelimiter(numbers);
        if (!customCustomDelimiter.isEmpty()) {
            delimiter = customCustomDelimiter;
            numbers = numbers.substring(numbers.indexOf(customDelimiterEndSign)+1);
        }

        if (delimiterAtTheEnd(numbers, delimiter))
            throw new Exception("No delimiter at the end allowed.");

        numbers = replaceNewLineWithDelimiter(numbers, delimiter);

        int indexOfNextDelimiter = numbers.indexOf(delimiter);
        int erasedCharacter = 0;
        int sum = 0;
        while (indexOfNextDelimiter > 0) {
            sum += getIntFromNumbers(numbers, indexOfNextDelimiter, delimiter, erasedCharacter);
            numbers = numbers.substring(indexOfNextDelimiter + delimiter.length());
            erasedCharacter += indexOfNextDelimiter + delimiter.length();
            indexOfNextDelimiter = numbers.indexOf(delimiter);
        }
        sum += getIntFromNumbers(numbers, numbers.length(), delimiter, erasedCharacter);

        return sum;
    }
    private static Integer getIntFromNumbers(String numbers, int indexOfNextDelimiter, String delimiter, int erasedCharacter) throws Exception {
        int number = 0;
        try {
            number = Integer.parseInt(numbers.substring(0, indexOfNextDelimiter));
        } catch (NumberFormatException e) {
            for (int i=0; i < numbers.length(); ++i){
                if (!Character.isDigit(numbers.charAt(i)))
                    throw new Exception(String.format("'%s' expected but '%c' found at position %d.", delimiter, numbers.charAt(i), i+erasedCharacter));
            }
        }
        return number;
    }

    private static boolean delimiterAtTheEnd(String numbers, String delimiter) {
        String endString = numbers.substring(numbers.length()-delimiter.length());
        return endString.equals(delimiter);
    }

    private static String checkCustomCustomDelimiter(String numbers){
        if (numbers.length() < customDelimiterStartSign.length())
            return "";
        if (!numbers.startsWith(customDelimiterStartSign)){
            return "";
        }
        int indexOfCustomDelimiterEnd = numbers.indexOf(customDelimiterEndSign);
        return numbers.substring(customDelimiterStartSign.length(), indexOfCustomDelimiterEnd);
    }

    private static String replaceNewLineWithDelimiter(String numbers, String delimeter){
       return numbers.replace("\n", delimeter);
    }
}
