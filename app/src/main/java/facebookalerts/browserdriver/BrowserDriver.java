package facebookalerts.browserdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserDriver {

    private WebDriver driver;

    public void start() {
        System.setProperty("webdriver.chrome.driver",
                "/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/home/mmarian/dev/facebook-alerts/app/src/main/resources/chromeprofile",
                "profile-directory=Profile 1");
        options.setBinary("/usr/bin/google-chrome-beta");

        this.driver = new ChromeDriver(options);
    }

    public void close() {
        this.driver.close();
    }

    public WebDriver get() {
        return this.driver;
    }
}
