package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By emailInput = By.id("email");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("submitLoginBtn");
    private By errorMessage = By.cssSelector(".alert.alert-danger");

    // Actions
    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isErrorDisplayed() {

        return driver.findElement(errorMessage).isDisplayed();

    }
public String getErrorMessage() {
    return driver.findElement(errorMessage).getText();
}

}
