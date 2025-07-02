package StringCalculatorTestPackage;

import StringCalculatorPackage.StringCalculator;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class StringCalculatorTest {
    @Test(priority=100)
    public void EmptyString() throws Exception {
        Assert.assertEquals(StringCalculator.Add(""), 0);
    }
    @Test(priority=200)
    public void OneNumber() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1"), 1);
    }
    @Test(priority=300)
    public void TwoNumbers() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1,2"), 3);
    }
    @Test(priority=350)
    public void ThreeNumbers() throws Exception{
        Assert.assertEquals(StringCalculator.Add("1,2,3"), 6);
    }
    @Test(priority=400)
    public void AddOneNinetyNineTimes() throws Exception {
        StringBuilder numbers = new StringBuilder();
        int ninetyNine = 99;
        numbers.append("1,".repeat(ninetyNine - 1));
        numbers.append("1");
        Assert.assertEquals(StringCalculator.Add(numbers.toString()), ninetyNine);
    }

    @Test(priority=500)
    public void AddOneRandomTimes() throws Exception {
        StringBuilder numbers = new StringBuilder();
        Random random = new Random();
        int randomNumber = random.nextInt(100)+1;
        numbers.append("1,".repeat(randomNumber - 1));
        numbers.append("1");
        Assert.assertEquals(StringCalculator.Add(numbers.toString()), randomNumber);
    }

    @Test(priority=600)
    public void NewLineInNumbers() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1,2\n3"), 6);
    }


    @Test(priority=650)
    public void NewLineInNumbers2() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1\n2\n3"), 6);
    }

    @Test(priority=700)
    public void NoDelimiterAtTheEndExceptionTest(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
    }

    @Test(priority=720)
    public void NoDelimiterAtTheEndExceptionWithMessage(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
        Assert.assertEquals(exception.getMessage(), "No delimiter at the end allowed.");
    }

    @Test(priority=800)
    public void handleDifferentDelimiter1() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//;\n1;3"), 4);
    }

    @Test(priority=820)
    public void handleDifferentDelimiter2() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//|\n1|2|3"), 6);
    }

    @Test(priority=840)
    public void handleDifferentDelimiter3() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//sep\n2sep5"), 7);
    }

    @Test(priority=860)
    public void handleDifferentDelimiterError(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
    }

    @Test(priority=870)
    public void handleDifferentDelimiterErrorWithMessage(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
        Assert.assertEquals(exception.getMessage(), "'|' expected but ',' found at position 3.");
    }

    @Test(priority=900)
    public void negativeNumbersException1(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,-2"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -2");
    }

    @Test(priority=920)
    public void negativeNumbersException2(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("2,-4,-9"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -4, -9");
    }
}
