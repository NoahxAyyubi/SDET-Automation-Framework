package test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test
    public void testPaginationNavigation() {

        driver.get(
                "https://qa-practice.razvanvancea.ro/pagination.html");

        // wait for page load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean foundPage3 = false;

        while (!foundPage3) {

            // get all pagination buttons
            List<WebElement> pages =
                    driver.findElements(
                            By.xpath("//ul[contains(@class,'pagination')]//a"));

            for (WebElement page : pages) {

                String pageText = page.getText();

                System.out.println(
                        "Checking pagination button: "
                                + pageText);

                // skip Previous button
                if (pageText.equals("Previous")) {
                    continue;
                }

                // click page 3 dynamically
                if (pageText.equals("3")) {

                    page.click();

                    System.out.println(
                            "Clicked page 3");

                    foundPage3 = true;

                    break;
                }
            }
        }

        // wait after page navigation
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // validate page result message displayed
        WebElement resultMessage =
                driver.findElement(
                        By.id("pageResult"));

        Assert.assertTrue(
                resultMessage.isDisplayed());

        System.out.println(
                "Pagination result displayed: "
                        + resultMessage.getText());

        System.out.println(
                "Pagination test passed");
    }
}