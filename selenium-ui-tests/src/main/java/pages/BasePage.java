package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForVisible(locator);
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        waitForVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        waitForVisible(locator);
        return driver.findElement(locator).getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void waitForAlert() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(5))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent());
    }
}