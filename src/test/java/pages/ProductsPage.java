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
        // 1. Click the cart icon
        cartIcon.click();

        // 2. Wait up to 5 seconds for the Checkout button to be visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }
}