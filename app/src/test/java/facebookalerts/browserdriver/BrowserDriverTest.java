package facebookalerts.browserdriver;

import org.junit.jupiter.api.Test;

public class BrowserDriverTest {
    @Test
    void testBrowserDriverStartAndClose() {
        BrowserDriver driver = new BrowserDriver();
        driver.start();
        driver.close();
    }
}
