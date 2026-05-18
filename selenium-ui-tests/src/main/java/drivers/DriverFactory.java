package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

   // private static WebDriver driver;
    // OLD shared driver (not thread-safe)

// private static WebDriver driver;

// NEW thread-safe driver storage

// NEW thread-safe driver storage
private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {

        WebDriverManager.chromedriver().setup();

        // OLD shared driver creation
        // driver = new ChromeDriver();

        // NEW thread-safe driver creation
        driver.set(new ChromeDriver());

        driver.get().manage().window().maximize();

        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {

        if (driver.get() != null) {

            driver.get().quit();

            driver.remove();
        }
    }
}