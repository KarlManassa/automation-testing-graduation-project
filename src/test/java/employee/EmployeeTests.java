package employee;

import base.BaseTest;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTests extends BaseTest {

    @Test(priority = 1)
    @Description("Test Case #4: Search for an existing employee")
    public void testSearchExistingEmployee() {
        new LoginPage().login("Admin", "admin123");

        DashboardPage dashboard = new DashboardPage();
        dashboard.navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.searchEmployee("Aaliyah Haq");

        Assert.assertTrue(
                pim.isRecordFound(),
                "Employee record not found!"
        );
    }

    @Test(priority = 2)
    @Description("Test Case #5: Search for a non-existing employee")
    public void testSearchNonExistingEmployee() {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();

        PIMPage pim = new PIMPage();
        pim.searchEmployee("NonExistentNameXYZ");

        Assert.assertTrue(
                pim.isNoRecordsFoundDisplayed(),
                "'No Records Found' message not displayed!"
        );
    }

    @Test(priority = 3)
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

    @Test(priority = 4)
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

    @Test(priority = 5)
    @Description("Test Case #8: End-to-end add new employee successfully")
    public void testAddEmployeeSuccessfully() {
        new LoginPage().login("Admin", "admin123");
        new DashboardPage().navigateToPIM();
        new PIMPage().goToAddEmployee();

        AddEmployeePage addEmp = new AddEmployeePage();
        addEmp.addEmployeeDetails("Michael", "Jordan");
        addEmp.clickSave();

        Assert.assertTrue(
                addEmp.isPersonalDetailsDisplayed(),
                "Personal Details page not opened!"
        );
    }
}
