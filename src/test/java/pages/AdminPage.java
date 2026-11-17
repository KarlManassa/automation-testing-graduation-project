package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement addButton;

    @FindBy(xpath = "//label[text()='User Role']")
    private WebElement userRoleField;

    @FindBy(xpath = "//label[text()='Employee Name']")
    private WebElement employeeNameField;

    @FindBy(xpath = "//label[text()='Username']")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Password']")
    private WebElement passwordField;

    @Step("Click Add User button")
    public void clickAddButton() {
        click(addButton);
    }

    @Step("Verify Add User form fields are displayed")
    public boolean areAddUserFieldsDisplayed() {
        return isElementDisplayed(userRoleField)
                && isElementDisplayed(employeeNameField)
                && isElementDisplayed(usernameField)
                && isElementDisplayed(passwordField);
    }
}
