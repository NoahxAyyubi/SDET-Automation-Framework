package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ChangePlanPage;
import pages.LoginPage;

public class SubscriptionUserFlow extends BaseTest {

    @Test
    public void testSubscriptionUserFlow() {

        // STEP 1: Login
        driver.get("https://qa-practice.razvanvancea.ro/auth_ecommerce.html");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("admin@admin.com", "admin123");

        

        // STEP 2: Navigate to dropdown page (simulate dashboard -> change plan)
        driver.get("https://qa-practice.razvanvancea.ro/dropdowns.html");

        // Dropdown locator (XPATH + class)
     
        ChangePlanPage changePlanPage = new ChangePlanPage(driver);
        changePlanPage.clickdropdown();
        changePlanPage.selectPlan("Bangladesh");

        System.out.println("Selected dropdown option: Bangladesh");

        // STEP 3: Navigate to alerts page
        driver.get("https://qa-practice.razvanvancea.ro/alerts.html");

        // Click confirm button (HTML button → triggers browser alert)
        changePlanPage.confirmChange();
changePlanPage.waitForAlert();
        System.out.println("Clicked confirm button");
        
        changePlanPage.waitForAlert();
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Alert text: " + alertText);
        Assert.assertTrue(alertText.contains("alert") || alertText.length() > 0);

        changePlanPage.acceptAlert();
    }
}
