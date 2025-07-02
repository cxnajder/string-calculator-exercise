package StringCalculatorPackage;

import java.util.ArrayList;

public class StringCalculator {
    public static int Add(String numbers){
        if (numbers.isEmpty()){
            return 0;
        }
        String[] separators = {",", "\n"};
        int index = indexOfNextNumber(numbers, separators);
        int sum = 0;
        while (index > 0) {
            sum += Integer.parseInt(numbers.substring(0, index));
            numbers = numbers.substring(index+1, numbers.length());
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
}
