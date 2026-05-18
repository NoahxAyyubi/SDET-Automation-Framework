package utils;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import drivers.DriverFactory;
import io.qameta.allure.Allure;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        // get thread-safe driver directly from DriverFactory
        WebDriver driver = DriverFactory.getDriver();

        ScreenshotUtil.capture(
                driver,
                result.getName());

        Allure.addAttachment(
                "Failure Screenshot",
                "image/png",
                new ByteArrayInputStream(
                        ((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES)),
                ".png");

        System.out.println(
                "Captured screenshot for failed test");
    }
}