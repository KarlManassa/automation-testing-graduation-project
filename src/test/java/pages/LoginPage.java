package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".oxd-alert-content-text")
    private WebElement errorMessage;

    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[1]")
    private WebElement usernameRequiredMessage;

    @FindBy(xpath = "(//span[contains(@class,'oxd-input-field-error-message')])[2]")
    private WebElement passwordRequiredMessage;

    public void enterUsername(String username) {
        type(usernameInput, username);
    }

    public void enterPassword(String password) {
        type(passwordInput, password);
    }

    public void clickLogin() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isErrorMessageDisplayed() {
        return isElementDisplayed(errorMessage);
    }

    public String getInvalidCredentialsError() {
        return waitForVisibility(errorMessage).getText();
    }

    public boolean areRequiredMessagesDisplayed() {
        return isElementDisplayed(usernameRequiredMessage)
                && isElementDisplayed(passwordRequiredMessage);
    }

    public boolean isRequiredValidationDisplayed() {
        return areRequiredMessagesDisplayed();
    }
}
