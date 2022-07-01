package facebookalerts.scraper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.UserNotificationRecord;

public class Scraper {
    public List<UserNotificationRecord> getUserNotifications(FacebookGroupRecord facebookGroup, Instant dateTime)
            throws InterruptedException {

        List<UserNotificationRecord> notificationList = new ArrayList<>();

        WebDriver driver = this.startDriver();

        driver.get(facebookGroup.facebookUrlId());

        Thread.sleep(5000);
        driver.close();

        return notificationList;
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