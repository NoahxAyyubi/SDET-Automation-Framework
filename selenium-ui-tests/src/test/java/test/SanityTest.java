package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class SanityTest extends BaseTest {

    @Test
    public void openHomePage() {

        driver.get("https://qa-practice.razvanvancea.ro/");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String title = driver.getTitle();
System.out.println("PAGE TITLE: " + title);
        Assert.assertTrue(title.contains("QA Practice"));
    }
}