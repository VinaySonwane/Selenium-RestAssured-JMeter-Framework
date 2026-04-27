package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import base.BaseTest;

public class LoginSteps {

    // If you are using a LoginPage object, declare it here
    // LoginPage loginPage;

    public LoginSteps() {
        // Initialize your page objects here using the shared BaseTest driver
        // loginPage = new LoginPage(BaseTest.getDriver());
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // We removed initializeDriver("chrome", ...).
        // Calling getDriver() will automatically run setupDriver() if the browser isn't open yet!
        BaseTest.getDriver().get("https://the-internet.herokuapp.com/login"); // Replace with your actual project URL
    }

    @When("I login using data from Excel row {int}")
    public void iLoginUsingDataFromExcelRow(Integer rowNumber) {
        // Your Excel Data-Driven logic goes here!
        // Example:
        // String username = excelReader.getCellData(rowNumber, 0);
        // String password = excelReader.getCellData(rowNumber, 1);
        // loginPage.enterUsername(username);
        // loginPage.enterPassword(password);
        // loginPage.clickLogin();
    }

    @Then("I should see the secure area dashboard")
    public void iShouldSeeTheSecureAreaDashboard() {
        // Your assertions go here!
        // Example:
        // String currentUrl = BaseTest.getDriver().getCurrentUrl();
        // Assert.assertTrue(currentUrl.contains("secure"), "User did not reach the dashboard!");
    }
}