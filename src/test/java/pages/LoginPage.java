package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By successMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        return driver.findElement(successMessage).getText().contains("You logged into a secure area!");
    }

    public static class CheckoutPage {
        WebDriver driver;

        // Locators for the Checkout Form
        @FindBy(id = "first-name")
        WebElement firstNameInput;

        @FindBy(id = "last-name")
        WebElement lastNameInput;

        @FindBy(id = "postal-code")
        WebElement zipCodeInput;

        @FindBy(id = "continue")
        WebElement continueBtn;

        // Locators for the Final Review Page
        @FindBy(className = "summary_total_label")
        WebElement totalPriceLabel;

        @FindBy(id = "finish")
        WebElement finishBtn;

        @FindBy(className = "complete-header")
        WebElement orderConfirmationMsg;

        public CheckoutPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public void enterCheckoutDetails(String firstName, String lastName, String zipCode) {
            // NEW: Wait up to 5 seconds for the First Name input box to physically render on the screen
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOf(firstNameInput));

            // Once visible, proceed with typing
            firstNameInput.sendKeys(firstName);
            lastNameInput.sendKeys(lastName);
            zipCodeInput.sendKeys(zipCode);
            continueBtn.click();
        }

        public String getTotalPrice() {
            return totalPriceLabel.getText();
        }

        public void finishCheckout() {
            finishBtn.click();
        }

        public String getConfirmationMessage() {
            return orderConfirmationMsg.getText();
        }
    }
}