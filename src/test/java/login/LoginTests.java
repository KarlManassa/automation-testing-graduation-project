package login;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.JsonReader;
import utils.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][]{
                {
                        JsonReader.getValue("validUser", "username"),
                        JsonReader.getValue("validUser", "password")
                }
        };
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][]{
                {
                        JsonReader.getValue("invalidUser", "username"),
                        JsonReader.getValue("invalidUser", "password")
                }
        };
    }

    @Test(
            priority = 1,
            dataProvider = "validLoginData",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Case #1: Login with valid credentials")
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();

        loginPage.login(username, password);

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/dashboard/index"),
                "URL does not contain dashboard!"
        );

        Assert.assertTrue(
                dashboardPage.isDashboardHeaderDisplayed(),
                "Dashboard header not visible!"
        );
    }

    @Test(
            priority = 2,
            dataProvider = "invalidLoginData",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #2: Login with invalid credentials")
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage();

        loginPage.login(username, password);

        Assert.assertEquals(
                loginPage.getInvalidCredentialsError(),
                "Invalid credentials",
                "Error message mismatch!"
        );
    }

    @Test(
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #3: Login with empty fields")
    public void testEmptyFieldsLogin() {
        LoginPage loginPage = new LoginPage();

        loginPage.login("", "");

        Assert.assertTrue(
                loginPage.isRequiredValidationDisplayed(),
                "Required validation message not displayed!"
        );
    }
}
