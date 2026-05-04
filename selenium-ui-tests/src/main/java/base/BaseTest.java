

package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import drivers.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
                driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}