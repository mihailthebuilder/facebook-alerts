package facebookalerts.scraper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.KeywordRecord;
import facebookalerts.records.UserNotificationRecord;

public class ScraperTest {
    @Test
    void getUserNotifications() throws InterruptedException {

        Scraper scraper = new Scraper();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);

        KeywordRecord keyword = new KeywordRecord("free", new String[] { "test@test.com" });

        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/FacebookGroupPage.html";

        FacebookGroupRecord group = new FacebookGroupRecord(htmlLocation, "yesterday",
                new KeywordRecord[] { keyword });

        List<UserNotificationRecord> notificationList = scraper.getUserNotifications(group, yesterday);
    }
}