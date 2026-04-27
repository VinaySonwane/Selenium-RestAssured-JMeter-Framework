package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
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
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        zipCodeInput.sendKeys(zipCode);
        continueBtn.click();
    }

    public String getTotalPrice() {
        // This will grab text like "Total: $43.18"
        return totalPriceLabel.getText();
    }

    public void finishCheckout() {
        finishBtn.click();
    }

    public String getConfirmationMessage() {
        return orderConfirmationMsg.getText();
    }
}