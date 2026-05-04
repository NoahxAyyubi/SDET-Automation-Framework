package test;


import java.util.List;

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
}