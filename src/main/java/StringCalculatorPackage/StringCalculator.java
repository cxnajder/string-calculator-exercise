package StringCalculatorPackage;

public class StringCalculator {

    static final String customDelimiterStartSign = "//";
    static final char customDelimiterEndSign = '\n';
    public static int Add(String numbers) throws Exception{
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
        int sum = 0;
        while (indexOfNextDelimiter > 0) {
            sum += Integer.parseInt(numbers.substring(0, indexOfNextDelimiter));
            numbers = numbers.substring(indexOfNextDelimiter + delimiter.length());
            indexOfNextDelimiter = numbers.indexOf(delimiter);
        }
        sum += Integer.parseInt(numbers);

        return sum;
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
