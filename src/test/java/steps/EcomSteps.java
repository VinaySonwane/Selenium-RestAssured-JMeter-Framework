package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.openqa.selenium.By;
import pages.ProductsPage;
import pages.CheckoutPage;
import base.BaseTest;

public class EcomSteps {

    ProductsPage productsPage;
    CheckoutPage checkoutPage;

    // Constructor: Initializes the Page Objects using your BaseTest driver
    public EcomSteps() {
        productsPage = new ProductsPage(BaseTest.getDriver());
        checkoutPage = new CheckoutPage(BaseTest.getDriver());
    }

    // 1. Navigate to the correct website
    @Given("I am on the SauceDemo login page")
    public void iAmOnSauceDemo() {
        BaseTest.getDriver().get("https://www.saucedemo.com/");
    }

    // 2. Perform the Login
    @When("I login with {string} and {string}")
    public void iLoginWith(String username, String password) {
        BaseTest.getDriver().findElement(By.id("user-name")).sendKeys(username);
        BaseTest.getDriver().findElement(By.id("password")).sendKeys(password);
        BaseTest.getDriver().findElement(By.id("login-button")).click();
    }

    // 3. Add items to the cart
    @When("I add {string} and {string} to the cart")
    public void iAddItemsToCart(String item1, String item2) {
        productsPage.addToCart(item1);
        productsPage.addToCart(item2);
    }

    // 4. Go to the checkout screen
    @When("I navigate to the checkout page")
    public void iNavigateToTheCheckoutPage() {
        productsPage.goToCart();
    }

    // 5. Fill out the shipping form
    @When("I enter checkout details with {string} {string} and {string}")
    public void iEnterCheckoutDetails(String firstName, String lastName, String zip) {
        checkoutPage.enterCheckoutDetails(firstName, lastName, zip);
    }

    // 6. Verify the Math
    @Then("the total price should correctly include tax and shipping")
    public void theTotalPriceShouldCorrectlyIncludeTax() {
        // The Backpack is $29.99 and Bike Light is $9.99. Plus Tax ($3.20). Total = $43.18
        String actualTotalText = checkoutPage.getTotalPrice();

        Assert.assertTrue(actualTotalText.contains("43.18"),
                "Expected total to be $43.18, but UI displayed: " + actualTotalText);
    }

    // 7. Verify the final success screen
    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeConfirmation(String expectedMessage) {
        checkoutPage.finishCheckout();

        String actualMessage = checkoutPage.getConfirmationMessage();

        Assert.assertEquals(actualMessage, expectedMessage);
    }
}