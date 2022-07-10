package facebookalerts.browserdriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserDriverTest {
    @Test
    void testBrowserDriverStartAndClose() {
        BrowserDriver driver = new BrowserDriver();
        driver.start();
        Class<? extends WebDriver> driverClass = driver.get().getClass();
        driver.close();
        assertEquals(ChromeDriver.class, driverClass);
    }
}
