package StringCalculatorPackage;

public class StringCalculator {

    static final String customDelimiterStartSign = "//";
    static final char customDelimiterEndSign = '\n';
    public static int Add(String numbers) throws Exception{
        if (numbers.isEmpty()){
            return 0;
        }
        String[] delimiters = {",", "\n"};

        String customCustomDelimiter = checkCustomCustomDelimiter(numbers);
        if (!customCustomDelimiter.isEmpty()) {
            delimiters[0] = customCustomDelimiter;
            numbers = numbers.substring(numbers.indexOf(customDelimiterEndSign)+1);
        }

        if (delimiterAtTheEnd(numbers, delimiters))
            throw new Exception("No delimiter at the end allowed.");

        int indexOfNextNumber = getIndexOfNextNumber(numbers, delimiters);
        int sum = 0;
        while (indexOfNextNumber > 0) {
            sum += Integer.parseInt(numbers.substring(0, indexOfNextNumber));
            numbers = numbers.substring(indexOfNextNumber+1);
            indexOfNextNumber = getIndexOfNextNumber(numbers, delimiters);
        }
        sum += Integer.parseInt(numbers);

        return sum;
    }

    private static int getIndexOfNextNumber(String numbers, String[] delimiters){
        int index = -1;
        for (String delimiter : delimiters) {
            int foundIndex = numbers.indexOf(delimiter);
            if (foundIndex >= 0) {
                if ((index < 0)||(index > foundIndex))
                    index = foundIndex;
            }
        }
        return index;
    }

    private static boolean delimiterAtTheEnd(String numbers, String[] delimiters) {
        String endString = "";
        for (String delimiter : delimiters) {
            if (endString.length() != delimiter.length())
                endString = numbers.substring(numbers.length()-delimiter.length());
            if (endString.equals(delimiter))
                return true;
        }
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
}
