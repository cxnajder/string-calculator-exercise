package StringCalculatorPackage;

public class StringCalculator {
    public static int Add(String numbers){
        if (numbers.length() == 0){
            return 0;
        }

        int index = numbers.indexOf(',');
        int sum = 0;
        while (index > 0) {
            sum += Integer.parseInt(numbers.substring(0, index));
            numbers = numbers.substring(index+1, numbers.length());
            index = numbers.indexOf(',');
        }
        sum += Integer.parseInt(numbers);

        return sum;
    }
}
