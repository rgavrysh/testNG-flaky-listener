import annotation.Flaky;
import db.DBase;
import listener.RetryListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

@Listeners(RetryListener.class)
public class SimpleTestExample {

    Random random = new Random();
    DBase db = new DBase();

    @Flaky(true)
    @Test
    public void testNumberComparing() {
        System.out.println("number comparing test");
        int randomNumber = random.nextInt(2);
        Assert.assertTrue(0 <= randomNumber);
    }

    @Test
    @Flaky
    public void testBoolean() {
        System.out.println("random boolean test");
        boolean bool = random.nextBoolean();
        System.out.println(bool);
        Assert.assertTrue(bool);
    }

    @Test
    public void testObjectNotNull() {
        System.out.println("not null object test");
        Object object = new Object();
        Assert.assertNotNull(object);
    }

    @Test
    public void testStringMatching() throws Exception {
        System.out.println("string matching test");
        Assert.assertEquals("james", "JAMES");
    }
}
