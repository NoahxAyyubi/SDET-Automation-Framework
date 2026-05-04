package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubscriptionDetailsPage extends BasePage {

    public SubscriptionDetailsPage(WebDriver driver) {
        super(driver);
    }

    private By planName = By.cssSelector(".plan-name");

    public String getPlanName() {
        return driver.findElement(planName).getText();
    }
}