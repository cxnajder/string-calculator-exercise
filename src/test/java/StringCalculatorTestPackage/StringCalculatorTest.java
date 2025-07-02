package StringCalculatorTestPackage;

import StringCalculatorPackage.StringCalculator;

import org.testng.Assert;
import org.testng.annotations.*;

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
}
