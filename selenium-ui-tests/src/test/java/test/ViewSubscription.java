package test;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SubscriptionDetailsPage;

public class ViewSubscription extends BaseTest {

    //
   // @Test
    public void testLogin() {

        driver.get("https://qa-practice.razvanvancea.ro/auth_ecommerce.html");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("admin", "admin123");
    }

   // @Test

    public void testInvalidLogin() {

        driver.get("https://qa-practice.razvanvancea.ro/auth_ecommerce.html");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("wrong", "wrong");

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed for invalid login");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Please try again"));
    }

   // @Test
    public void testViewSubscription() {

        driver.get("https://qa-practice.razvanvancea.ro/dynamic-table.html");

        DashboardPage dashboard = new DashboardPage(driver);

        dashboard.clickFirstSubscription();

        SubscriptionDetailsPage details = new SubscriptionDetailsPage(driver);

        Assert.assertTrue(details.getPlanName().contains("Basic"));
    }

   //@Test
    public void testTableBasics() {

        driver.get("https://qa-practice.razvanvancea.ro/dynamic-table.html");

        // navigate to table page manually if needed (or add later)

        DashboardPage dashboard = new DashboardPage(driver);

        // 1. Validate row count
        int rowCount = dashboard.getRowCount();
        System.out.println("Row count: " + rowCount);

        Assert.assertTrue(rowCount > 0, "Table should have rows");

        // 2. Click first row
        dashboard.clickFirstSubscription();

        // optional: just confirm no crash (for now)
        System.out.println("Clicked first row successfully");

        dashboard.clickRowByFirstName("joel");


    }

    @Test
    public void testGetRowDataByName() {

        driver.get("https://qa-practice.razvanvancea.ro/dynamic-table.html");

        DashboardPage dashboard = new DashboardPage(driver);

        // Call your method
     List<String> rowData = dashboard.getRowDtaByFirstName("Michael");

        // Debug print
        System.out.println("Row Data: " + rowData);

        // Basic validations
        Assert.assertNotNull(rowData, "Row data should not be null");
        Assert.assertTrue(rowData.size() > 0, "Row should have data");

        // Validate specific columns (based on table structure)
        Assert.assertEquals(rowData.get(1), "Michael");
        Assert.assertTrue(rowData.get(4).contains("@"));
    }

     // @Test

    public void testDatePicker() {

        // STEP 1: Open page

        driver.get("https://qa-practice.razvanvancea.ro/calendar.html");

        // STEP 2: Open calendar

        By dateInput = By.xpath("//div[contains(@class,'input-group date')]//input");

        driver.findElement(dateInput).click();

        System.out.println("Opened calendar");

        // STEP 3: Verify calendar popup appeared

        By calendarPopup =

                By.xpath("//div[contains(@class,'datepicker-days')]");

        Assert.assertTrue(driver.findElement(calendarPopup).isDisplayed());

        System.out.println("Calendar popup displayed");

        // STEP 4: Select a specific day

        String targetDay = "15";

        By allDays =

                By.xpath("//td[contains(@class,'day')]");

        java.util.List<WebElement> days =

                driver.findElements(allDays);

        for (WebElement day : days) {

            String dayText = day.getText();

            System.out.println("Checking day: " + dayText);

            // avoid selecting old/new month dates

            String className = day.getAttribute("class");

            if (dayText.equals(targetDay)

                    && !className.contains("old")

                    && !className.contains("new")) {

                day.click();

                System.out.println("Selected day: " + targetDay);

                break;

            }

        }

        // STEP 5: Validate input now contains selected date

        String selectedDate =

                driver.findElement(dateInput).getAttribute("value");

        System.out.println("Selected date value: " + selectedDate);

        Assert.assertTrue(selectedDate.contains("15"));

        System.out.println("Date picker test passed");

    }

@Test
public void testIframeActions() {

    // STEP 1: Open iframe page

    driver.get("https://qa-practice.razvanvancea.ro/iframe.html");

    // STEP 2: Scroll to iframe

    WebElement iframe = driver.findElement(
            By.xpath("//iframe[@id='iframe-checkboxes']"));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", iframe);

    System.out.println("Scrolled to iframe");


    // STEP 3: Switch into iframe

    driver.switchTo().frame(iframe);

    System.out.println("Switched into iframe");


    // STEP 4: Scroll INSIDE iframe

    WebElement learnMoreButton = driver.findElement(
            By.xpath("//a[@id='learn-more']"));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].scrollIntoView(true);", learnMoreButton);

    System.out.println("Scrolled inside iframe to Learn More button");


    // STEP 5: Click button inside iframe

    learnMoreButton.click();

    System.out.println("Clicked Learn More button inside iframe");


    // STEP 6: Validate button displayed

    Assert.assertTrue(learnMoreButton.isDisplayed());

    System.out.println("Learn More button displayed successfully");


    // STEP 7: Switch back to main page

    driver.switchTo().defaultContent();

    System.out.println("Switched back to main page");


    // STEP 8: Validate iframe still visible on main page

    Assert.assertTrue(driver.findElement(
            By.xpath("//iframe[@id='iframe-checkboxes']"))
            .isDisplayed());

    System.out.println("Iframe test passed");
}


}