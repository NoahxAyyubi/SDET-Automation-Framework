package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Attachment;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String testName) {

        File src = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File dest = new File(
                "target/screenshots/" + testName + ".png");

        dest.getParentFile().mkdirs();

        try {

            Files.copy(src.toPath(), dest.toPath());

            System.out.println(
                    "Screenshot saved: " + dest.getAbsolutePath());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // sends screenshot bytes into Allure report attachments
    @Attachment(value = "Failure Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}