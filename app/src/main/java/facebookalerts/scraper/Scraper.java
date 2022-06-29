package facebookalerts.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import facebookalerts.datastore.FacebookGroupRecord;

public class Scraper {
    public void getPosts(FacebookGroupRecord facebookGroup)
            throws InterruptedException {

        WebDriver driver = this.startDriver();

        driver.get(facebookGroup.facebookUrlId());

        Thread.sleep(5000);
        driver.close();
    }

    private WebDriver startDriver() {
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "/src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=./src/main/resources/chromeprofile", "profile-directory=Profile 1");
        WebDriver driver = new ChromeDriver(options);
        return driver;
    }
}