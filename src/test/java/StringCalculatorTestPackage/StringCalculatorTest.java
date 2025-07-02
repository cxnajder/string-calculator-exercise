package StringCalculatorTestPackage;

import StringCalculatorPackage.StringCalculator;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class StringCalculatorTest {
    @Test(priority=100)
    public void EmptyString(){
        try {
            Assert.assertEquals(StringCalculator.Add(""), 0);
        } catch (Exception e) {Assert.fail();}
    }
    @Test(priority=200)
    public void OneNumber(){
        try {
            Assert.assertEquals(StringCalculator.Add("1"), 1);
        } catch (Exception e) {Assert.fail();}
    }
    @Test(priority=300)
    public void TwoNumbers(){
        try {
            Assert.assertEquals(StringCalculator.Add("1,2"), 3);
        } catch (Exception e) {Assert.fail();}
    }

    @Test(priority=400)
    public void AddOneNinetyNineTimes(){
        String numbers = new String("");
        int ninetyNine = 99;
        for (int i=1; i < ninetyNine; ++i)
        {
            numbers += "1,";
        }
        numbers += "1";
        try {
            Assert.assertEquals(StringCalculator.Add(numbers), ninetyNine);
        } catch (Exception e) {Assert.fail();}
    }

    @Test(priority=500)
    public void AddOneRandomTimes(){
        String numbers = new String("");
        Random random = new Random();
        int randomNumber = random.nextInt(100)+1;
        for (int i=1; i < randomNumber; ++i)
        {
            numbers += "1,";
        }
        numbers += "1";
        try {
            Assert.assertEquals(StringCalculator.Add(numbers), randomNumber);
        } catch (Exception e) {Assert.fail();}
    }

    @Test(priority=600)
    public void NewLineInNumbers(){
        try {
            Assert.assertEquals(StringCalculator.Add("1,2\n3"), 6);
        } catch (Exception e) {Assert.fail();}
    }


    @Test(priority=650)
    public void NewLineInNumbers2(){
        try {
            Assert.assertEquals(StringCalculator.Add("1\n2\n3"), 6);
        } catch (Exception e) {Assert.fail();}
    }

    @Test(priority=700)
    public void NoSeparatorAtTheEndExceptionTest(){
        Exception except = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
    }

    @Test(priority=720)
    public void NoSeparatorAtTheEndExceptionWithMessage(){
        Exception except = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("1,2,"));
        Assert.assertEquals(except.getMessage(), "No separator at the end allowed");
    }

    @Test(priority=800)
    public void handleDifferentDelimiter1(){
        try {
            Assert.assertEquals(StringCalculator.Add("//;\\n1;3"), 4);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test(priority=820)
    public void handleDifferentDelimiter2(){
        try {
            Assert.assertEquals(StringCalculator.Add("//|\\n1|2|3"), 6);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test(priority=840)
    public void handleDifferentDelimiter3(){
        try {
            Assert.assertEquals(StringCalculator.Add("//sep\\n2sep5"), 7);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test(priority=860)
    public void handleDifferentDelimiterError(){
        Exception except = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
    }

    @Test(priority=870)
    public void handleDifferentDelimiterErrorWithMessage(){
        Exception exception = Assert.expectThrows(Exception.class, () -> StringCalculator.Add("//|\n1|2,3"));
        Assert.assertEquals(exception.getMessage(), "'|' expected but ',' found at position 3.");
    }
}
