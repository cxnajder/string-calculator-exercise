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
    public void ThreeNumbers() throws Exception {
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
    public void NewLineInNumbers1() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1,2\n3"), 6);
    }


    @Test(priority=650)
    public void NewLineInNumbers2() throws Exception {
        Assert.assertEquals(StringCalculator.Add("1\n2\n3"), 6);
    }

    @Test(priority=700)
    public void NoDelimiterAtTheEndExceptionTest() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
    }

    @Test(priority=720)
    public void NoDelimiterAtTheEndExceptionWithMessage() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
        Assert.assertEquals(exception.getMessage(), "No delimiter at the end allowed.");
    }

    @Test(priority=800)
    public void HandleDifferentDelimiter1() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//;\n1;3"), 4);
    }

    @Test(priority=820)
    public void HandleDifferentDelimiter2() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//|\n1|2|3"), 6);
    }

    @Test(priority=840)
    public void HandleDifferentDelimiter3() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//sep\n2sep5"), 7);
    }

    @Test(priority=845)
    public void HandleDifferentDelimiterWithMinus() throws Exception {
        Assert.assertEquals(StringCalculator.Add("//sep-\n2sep-5"), 7);
    }

    @Test(priority=860)
    public void HandleDifferentDelimiterError() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
    }

    @Test(priority=870)
    public void HandleDifferentDelimiterErrorWithMessage() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
        Assert.assertEquals(exception.getMessage(), "'|' expected but ',' found at position 3.");
    }

    @Test(priority=900)
    public void NegativeNumbersException1() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,-2"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -2");
    }

    @Test(priority=845)
    public void NegativeNumbersExceptionWithDifferentDelimiterWithMinus() throws Exception {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//sep-\n2sep--5"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -5");
    }

    @Test(priority=920)
    public void NegativeNumbersException2() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("2,-4,-9"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -4, -9");
    }

    @Test(priority=950)
    public void MultipleErrorsTest() {
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,-3"));
        Assert.assertEquals(exception.getMessage(), "Negative number(s) not allowed: -3\n'|' expected but ',' found at position 3.");
    }
    @Test(priority=999)
    public void DoNotIgnoreLessThanThousand() throws Exception {
        Assert.assertEquals(StringCalculator.Add("2,999"), 1001);
    }

    @Test(priority=1000)
    public void DoNotIgnoreThousand() throws Exception {
        Assert.assertEquals(StringCalculator.Add("2,1000"), 1002);
    }

    @Test(priority=1001)
    public void IgnoreHigherThanThousand() throws Exception {
        Assert.assertEquals(StringCalculator.Add("2,1001"), 2);
    }
}
