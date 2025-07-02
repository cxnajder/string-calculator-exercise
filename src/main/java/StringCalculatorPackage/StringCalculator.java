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

        int indexOfNextDelimiter = getIndexOfNextDelimiter(numbers, delimiter);
        int sum = 0;
        while (indexOfNextDelimiter > 0) {
            sum += Integer.parseInt(numbers.substring(0, indexOfNextDelimiter));
            numbers = numbers.substring(indexOfNextDelimiter + delimiter.length());
            indexOfNextDelimiter = getIndexOfNextDelimiter(numbers, delimiter);
        }
        sum += Integer.parseInt(numbers);

        return sum;
    }

    private static int getIndexOfNextDelimiter(String numbers, String delimiter){
        int index = -1;
        int foundIndex = numbers.indexOf(delimiter);
        if (foundIndex >= 0) {
            if ((index < 0)||(index > foundIndex))
                index = foundIndex;
        }

        return index;
    }

    private static boolean delimiterAtTheEnd(String numbers, String delimiter) {
        String endString = "";
        if (endString.length() != delimiter.length())
            endString = numbers.substring(numbers.length()-delimiter.length());
        if (endString.equals(delimiter))
            return true;

        return false;
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
