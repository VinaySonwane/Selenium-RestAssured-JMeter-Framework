package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductsPage {
    WebDriver driver;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(id = "checkout")
    WebElement checkoutBtn;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addToCart(String itemName) {
        String xpath = "//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button";
        driver.findElement(By.xpath(xpath)).click();
    }

    public void goToCart() {
        // Increased wait time to 10 seconds for stability
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Wait for the red cart badge to ensure React has finished updating the state
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        // 2. Standard Selenium click
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();

        // 3. BULLETPROOFING: Halt execution until the URL physically changes to the cart page
        wait.until(ExpectedConditions.urlContains("cart.html"));

        // 4. Now we know we are on the cart page. Click checkout.
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();

        // 5. BULLETPROOFING: Halt execution until the URL physically changes to the checkout form
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }
}