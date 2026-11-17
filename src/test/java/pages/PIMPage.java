package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PIMPage extends BasePage {

    @FindBy(xpath = "//a[text()='Employee List']")
    private WebElement employeeListTab;

    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeTab;

    @FindBy(xpath = "//label[text()='Employee Name']/ancestor::div[contains(@class,'oxd-input-group')]//input")
    private WebElement employeeNameSearchField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;

    private final By employeeSuggestion =
            By.xpath("//div[contains(@class,'oxd-autocomplete-option')]");

    private final By recordFoundText =
            By.xpath("//span[contains(normalize-space(),'Record Found') or contains(normalize-space(),'Records Found')]");

    private final By noRecordsFoundText =
            By.xpath("//span[normalize-space()='No Records Found']");

    @Step("Go to Employee List")
    public void goToEmployeeList() {
        click(employeeListTab);
    }

    @Step("Go to Add Employee")
    public void goToAddEmployee() {
        click(addEmployeeTab);
    }

    @Step("Search employee by name: {0}")
    public void searchEmployee(String name) {
        type(employeeNameSearchField, name);

        try {
            WebElement suggestion = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(employeeSuggestion)
            );
            suggestion.click();
        } catch (Exception e) {
            employeeNameSearchField.clear();
            employeeNameSearchField.sendKeys(name);
        }

        click(searchButton);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(recordFoundText),
                ExpectedConditions.visibilityOfElementLocated(noRecordsFoundText)
        ));
    }

    @Step("Verify employee record is found")
    public boolean isRecordFound() {
        return !driver.findElements(recordFoundText).isEmpty()
                && driver.findElement(recordFoundText).isDisplayed();
    }

    @Step("Verify no records found message")
    public boolean isNoRecordsFoundDisplayed() {
        return !driver.findElements(noRecordsFoundText).isEmpty()
                && driver.findElement(noRecordsFoundText).isDisplayed();
    }
}
