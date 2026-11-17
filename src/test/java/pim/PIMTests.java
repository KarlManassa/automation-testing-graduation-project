package pim;

import base.BaseTest;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import utils.JsonReader;
import utils.RetryAnalyzer;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PIMTests extends BaseTest {

    @DataProvider(name = "existingEmployeeData")
    public Object[][] existingEmployeeData() {
        return new Object[][]{
                {JsonReader.getValue("employee", "searchName")}
        };
    }

    @DataProvider(name = "nonExistingEmployeeData")
    public Object[][] nonExistingEmployeeData() {
        return new Object[][]{
                {JsonReader.getValue("nonExistingEmployee", "searchName")}
        };
    }

    @DataProvider(name = "newEmployeeData")
    public Object[][] newEmployeeData() {
        return new Object[][]{
                {
                        JsonReader.getValue("employee", "firstName"),
                        JsonReader.getValue("employee", "lastName")
                }
        };
    }

    @Test(
            priority = 1,
            dataProvider = "existingEmployeeData",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #4: Search for an existing employee")
    public void testSearchExistingEmployee(String employeeName) {
        new LoginPage().login("Admin", "admin123");

        new DashboardPage().navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.searchEmployee(employeeName);

        Assert.assertTrue(
                pim.isRecordFound(),
                "Employee record not found!"
        );
    }

    @Test(
            priority = 2,
            dataProvider = "nonExistingEmployeeData",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #5: Search for a non-existing employee")
    public void testSearchNonExistingEmployee(String employeeName) {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.searchEmployee(employeeName);

        Assert.assertTrue(
                pim.isNoRecordsFoundDisplayed(),
                "'No Records Found' message not displayed!"
        );
    }

    @Test(
            priority = 3,
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #6: Open Add Employee page")
    public void testOpenAddEmployeePage() {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.goToAddEmployee();

        Assert.assertTrue(
                getDriver().getCurrentUrl().contains("/pim/addEmployee"),
                "Not on Add Employee page!"
        );
    }

    @Test(
            priority = 4,
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case #7: Add employee with empty required field")
    public void testAddEmployeeEmptyRequiredField() {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();
        new PIMPage().goToAddEmployee();

        AddEmployeePage addEmp = new AddEmployeePage();
        addEmp.addEmployeeDetails("", "Smith");
        addEmp.clickSave();

        Assert.assertTrue(
                addEmp.isRequiredErrorDisplayed(),
                "Required validation under First Name missing!"
        );
    }

    @Test(
            priority = 5,
            dataProvider = "newEmployeeData",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Case #8: End-to-end add new employee successfully")
    public void testAddEmployeeSuccessfully(String firstName, String lastName) {
        String uniqueFirstName = firstName + System.currentTimeMillis();

        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();
        new PIMPage().goToAddEmployee();

        AddEmployeePage addEmp = new AddEmployeePage();
        addEmp.addEmployeeDetails(uniqueFirstName, lastName);
        addEmp.clickSave();

        Assert.assertTrue(
                addEmp.isPersonalDetailsDisplayed(),
                "Personal Details page not opened!"
        );

        new DashboardPage().navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.goToEmployeeList();
        pim.searchEmployee(uniqueFirstName + " " + lastName);

        Assert.assertTrue(
                pim.isRecordFound(),
                "New employee was not found in Employee List!"
        );
    }
}
