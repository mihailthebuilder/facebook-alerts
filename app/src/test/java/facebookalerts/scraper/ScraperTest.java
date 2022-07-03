package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.KeywordRecord;
import facebookalerts.records.UserNotificationRecord;

public class ScraperTest {
    @Test
    void getUserNotifications() throws InterruptedException {

        Scraper scraper = new Scraper();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);

        KeywordRecord keyword = new KeywordRecord("sentient", new String[] { "test@test.com" });

        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/FacebookGroupPage.html";

        FacebookGroupRecord group = new FacebookGroupRecord(htmlLocation, "yesterday",
                new KeywordRecord[] { keyword });

        List<UserNotificationRecord> notificationList = scraper.getUserNotifications(group, yesterday);

        assertEquals(1, notificationList.size());

        UserNotificationRecord notification = notificationList.get(0);
        assertEquals("test@test.com", notification.emailAddress());
        assertEquals(1, notification.posts());

        assertTrue(notification.posts()[0].contains("Recently there has been a lot of noise"));
    }

    @Test
    void startDriverTest() {
        Scraper scraper = new Scraper();
        WebDriver driver = scraper.startDriver();
        driver.close();
    }
}