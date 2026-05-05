package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePlanPage extends BasePage {

    public ChangePlanPage(WebDriver driver) {
        super(driver);
    }

    // 🔥 using XPATH + CLASS ONLY (your rule)

    private By dropdown = By.xpath("//select[contains(@class,'custom-select')]");
    private By confirmBtn = By.xpath("//button[contains(@class,'btn btn-secondary')]");
    private By successMsg = By.xpath("//div[contains(@class,'alert')]");

    // Actions
    public void clickdropdown() {
        click(dropdown);
    }

    public void selectPlan(String plan) {
        org.openqa.selenium.support.ui.Select select =
            new org.openqa.selenium.support.ui.Select(driver.findElement(dropdown));

        select.selectByVisibleText(plan);
    }

    public void confirmChange() {
        click(confirmBtn);
    }

    public String getSuccessMessage() {
        return getText(successMsg);
    }
}