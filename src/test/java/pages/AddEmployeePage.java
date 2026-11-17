package pages;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePage extends BasePage {

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(css = "button[type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//span[contains(@class,'oxd-input-field-error-message')]")
    private WebElement requiredError;

    @FindBy(xpath = "//h6[text()='Personal Details']")
    private WebElement personalDetailsHeader;

    public void addEmployeeDetails(String firstName, String lastName) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
    }

    public void clickSave() {
        click(saveButton);
    }

    public boolean isRequiredErrorDisplayed() {
        return isElementDisplayed(requiredError);
    }

    public boolean isPersonalDetailsDisplayed() {
        return isElementDisplayed(personalDetailsHeader);
    }
}
