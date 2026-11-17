package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(xpath = "//span[normalize-space()='PIM']")
    private WebElement pimMenu;

    @FindBy(xpath = "//span[normalize-space()='Admin']")
    private WebElement adminMenu;

    @FindBy(xpath = "//a[contains(@href,'orangehrm.com')]")
    private WebElement footerLink;

    @Step("Verify Dashboard header is displayed")
    public boolean isDashboardHeaderDisplayed() {
        return isElementDisplayed(dashboardHeader);
    }

    @Step("Navigate to PIM module")
    public void navigateToPIM() {
        click(pimMenu);
    }

    @Step("Navigate to Admin module")
    public void navigateToAdmin() {
        click(adminMenu);
    }

    @Step("Click OrangeHRM footer link")
    public void clickFooterLink() {
        click(footerLink);
    }

    @Step("Get footer text")
    public String getFooterText() {
        return wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions
                        .visibilityOfElementLocated(
                                By.className("oxd-layout-footer")
                        )
        ).getText();
    }

    @Step("Verify sidebar menu item: {0}")
    public boolean isSidebarMenuDisplayed(String menu) {
        try {
            WebElement menuItem = wait.until(
                    org.openqa.selenium.support.ui.ExpectedConditions
                            .visibilityOfElementLocated(
                                    By.xpath(
                                            "//nav//span[normalize-space()='" + menu + "']"
                                    )
                            )
            );

            return menuItem.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}
