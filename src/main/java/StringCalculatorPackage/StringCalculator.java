package StringCalculatorPackage;

public class StringCalculator {

    static final String delimiterStartSign = "//";
    static final char delimiterEndSign = '\n';
    public static int Add(String numbers) throws Exception{
        if (numbers.isEmpty()){
            return 0;
        }
        String[] separators = {",", "\n"};

        String customDelimiter = checkCustomDelimiter(numbers);
        if (!customDelimiter.isEmpty()) {
            separators[0] = customDelimiter;
            numbers = numbers.substring(numbers.indexOf(delimiterEndSign)+1);
        }

        if (separatorAtTheEnd(numbers, separators))
            throw new Exception("No separator at the end allowed");

        int index = indexOfNextNumber(numbers, separators);
        int sum = 0;
        while (index > 0) {
            sum += Integer.parseInt(numbers.substring(0, index));
            numbers = numbers.substring(index+1);
            index = indexOfNextNumber(numbers, separators);
        }
        sum += Integer.parseInt(numbers);

        return sum;
    }

    private static int indexOfNextNumber(String numbers, String[] separators){
        int index = -1;
        for (String separator : separators) {
            int foundIndex = numbers.indexOf(separator);
            if (foundIndex >= 0) {
                if ((index < 0)||(index > foundIndex))
                    index = foundIndex;
            }
        }
        return index;
    }

    private static boolean separatorAtTheEnd(String numbers, String[] separators) {
        String endString = "";
        for (String separator : separators) {
            if (endString.length() != separator.length())
                endString = numbers.substring(numbers.length()-separator.length());
            if (endString.equals(separator))
                return true;
        }
        return false;
    }

    private static String checkCustomDelimiter(String numbers){
        if (numbers.length() < delimiterStartSign.length())
            return "";
        if (!numbers.startsWith(delimiterStartSign)){
            return "";
        }
        int indexOfDelimiterEnd = numbers.indexOf(delimiterEndSign);
        return numbers.substring(delimiterStartSign.length(), indexOfDelimiterEnd);
    }
}
