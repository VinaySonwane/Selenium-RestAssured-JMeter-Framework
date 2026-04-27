package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.time.Duration;

public class BaseTest {

    // Using ThreadLocal ensures that if you run multiple tests in parallel,
    // their WebDrivers do not collide and crash each other.
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // This method spins up the browser and applies our security bypasses
    public static void setupDriver() {
        ChromeOptions options = new ChromeOptions();

        // --- THE POPUP FIX ---
        // 1. Disable the "Save Password" and credential manager prompts
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        // 2. Disable Chrome's native "Data Breach / Leak Detection" warning
        options.addArguments("--disable-features=PasswordLeakDetection");

        // 3. Disable standard web notifications and maximize the screen
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");

        try {
            // Connect to your active Selenium Grid
            WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

            // Set a baseline implicit wait
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Assign this specific browser window to the current test thread
            driver.set(webDriver);

        } catch (MalformedURLException e) {
            System.err.println("Failed to connect to Selenium Grid!");
            e.printStackTrace();
        }
    }

    // This is the exact method your EcomSteps and LoginSteps are calling
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            setupDriver(); // Failsafe: start it if it isn't running yet
        }
        return driver.get();
    }

    // Call this in your @After hooks to close the browser gracefully
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}