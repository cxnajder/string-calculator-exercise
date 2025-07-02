package StringCalculatorTestPackage;

import StringCalculatorPackage.StringCalculator;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Random;

public class StringCalculatorTest {
    @Test(priority=100)
    public void EmptyString(){
        Assert.assertEquals(StringCalculator.Add(""), 0);
    }
    @Test(priority=200)
    public void OneNumber(){
        Assert.assertEquals(StringCalculator.Add("1"), 1);
    }
    @Test(priority=300)
    public void TwoNumbers(){
        Assert.assertEquals(StringCalculator.Add("1,2"), 3);
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
        Assert.assertEquals(StringCalculator.Add(numbers), ninetyNine);
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
        Assert.assertEquals(StringCalculator.Add(numbers), randomNumber);
    }

    @Test(priority=600)
    public void NewLineInNumbers(){
        Assert.assertEquals(StringCalculator.Add("1,2\n3"), 6);
    }

    @Test(priority=650)
    public void NewLineInNumbers2(){
        Assert.assertEquals(StringCalculator.Add("1\n2\n3"), 6);
    }
}
