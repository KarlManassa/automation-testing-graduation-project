
package footerandsidebar;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.RetryAnalyzer;
import io.qameta.allure.Description;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class FooterAndSidebarTests extends BaseTest {

    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    @Description("Test Case #10: Verify footer branding link")
    public void testFooterBrandingLink() {
        new LoginPage().login("Admin", "admin123");

        DashboardPage dashboard = new DashboardPage();

        Assert.assertTrue(
                dashboard.getFooterText().contains("OrangeHRM"),
                "Footer branding text is missing!"
        );

        String mainWindow = getDriver().getWindowHandle();

        dashboard.clickFooterLink();

        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(driver ->
                        driver.getWindowHandles().size() > 1
                                || driver.getCurrentUrl().contains("orangehrm.com")
                );

        Set<String> windows = getDriver().getWindowHandles();

        for (String window : windows) {
            if (!window.equals(mainWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("orangehrm.com"),
                "Footer link did not open OrangeHRM website!"
        );
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    @Description("Test Case #11: Verify sidebar menu UI items")
    public void testSidebarMenuUI() {
        new LoginPage().login("Admin", "admin123");

        DashboardPage dashboard = new DashboardPage();

        String[] expectedMenus = {
                "Admin",
                "PIM",
                "Leave",
                "Time",
                "Recruitment",
                "My Info",
                "Performance",
                "Dashboard",
                "Directory"
        };

        for (String menu : expectedMenus) {
            Assert.assertTrue(
                    dashboard.isSidebarMenuDisplayed(menu),
                    "Sidebar menu item missing: " + menu
            );
        }
    }
}
