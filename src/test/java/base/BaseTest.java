package base;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal =
            new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("") String browserName) {

        String browser = browserName.isEmpty()
                ? ConfigReader.getProperty("browser")
                : browserName;

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else {

            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        driverThreadLocal.set(driver);

        getDriver().manage().window().maximize();

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ZERO);

        getDriver().get(
                ConfigReader.getProperty("base.url") + "auth/login"
        );
    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        try {

            if (result.getStatus() == ITestResult.FAILURE) {
                attachScreenshot();
            }

        } finally {

            WebDriver driver = getDriver();

            if (driver != null) {
                driver.quit();
            }

            driverThreadLocal.remove();
        }
    }

    @Attachment(
            value = "Failure Screenshot",
            type = "image/png"
    )
    public byte[] attachScreenshot() {

        WebDriver driver = getDriver();

        if (driver != null) {
            return ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);
        }

        return new byte[0];
    }
}
