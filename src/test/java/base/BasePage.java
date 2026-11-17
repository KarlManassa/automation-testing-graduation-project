package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigReader;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {

        this.driver = BaseTest.getDriver();

        int waitTime = Integer.parseInt(
                ConfigReader.getProperty("explicit.wait")
        );

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(waitTime)
        );

        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForVisibility(WebElement element) {

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    protected WebElement waitForClickable(WebElement element) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );
    }

    protected void click(WebElement element) {

        waitForClickable(element).click();
    }

    protected void type(WebElement element, String text) {

        WebElement visibleElement = waitForVisibility(element);

        visibleElement.clear();
        visibleElement.sendKeys(text);
    }

    protected boolean isElementDisplayed(WebElement element) {

        try {
            return waitForVisibility(element).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}
