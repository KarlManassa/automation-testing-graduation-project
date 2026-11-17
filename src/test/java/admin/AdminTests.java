package admin;

import base.BaseTest;
import pages.AdminPage;
import pages.DashboardPage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTests extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #9: Verify Admin > Add User page fields")
    public void testVerifyAddUserFields() {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToAdmin();

        AdminPage admin = new AdminPage();
        admin.clickAddButton();

        Assert.assertTrue(
                admin.areAddUserFieldsDisplayed(),
                "Add User fields are missing!"
        );
    }
}
