package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // table rows
    private By rows = By.cssSelector("#data-table tbody tr");

    // Actions
    public void clickFirstSubscription() {
        driver.findElements(rows).get(0).click();
    }

    public int getRowCount() {
        return driver.findElements(rows).size();
    }

    public void clickRowByFirstName(String name) {

        for (var row : driver.findElements(rows)) {

            String rowText = row.getText();

            // 🔍 print every row (debug)

            System.out.println("Row: " + rowText);

            if (rowText.contains(name)) {

                System.out.println("✅ Found match: " + name);

                row.click();

                return;

            }

        }

        // ❌ if not found

        System.out.println("❌ No match found for: " + name);

    }
        
    public String getEmailByFirstName(String name) {

        for (WebElement row : driver.findElements(rows)) {

            List<WebElement> cells = row.findElements(By.tagName("td"));

            String firstName = cells.get(1).getText();
            String email = cells.get(4).getText();

            if (firstName.equalsIgnoreCase(name)) {
                return email;
            }
        }

        return null;
    }
    public List<String> getRowDtaByFirstName (String name) {

        for (WebElement row : driver.findElements(rows)) {

            List<WebElement> cells = row.findElements(By.tagName("td"));

            String firstName = cells.get(1).getText();
      

            if (firstName.equalsIgnoreCase(name)) {
                List<String> celltext = new java.util.ArrayList<>();
                for(WebElement cell : cells) {
                    celltext.add(cell.getText());
                }
                return celltext;
            }
        }
        return null;
    }

}