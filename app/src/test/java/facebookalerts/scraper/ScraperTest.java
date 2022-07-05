package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.KeywordRecord;

public class ScraperTest {

    @Test
    void getUserNotificationsTest() throws InterruptedException {

        Scraper scraper = new Scraper();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);

        KeywordRecord keyword = new KeywordRecord("sentient", Arrays.asList(new String[] { "test@test.com" }));

        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/GroupPage.html";

        FacebookGroupRecord group = new FacebookGroupRecord(htmlLocation, "yesterday",
                Arrays.asList(new KeywordRecord[] { keyword }));

        Map<String, List<String>> notifications = scraper.getUserNotifications(group, yesterday);

        assertNotNull(notifications.get("test@test.com"));

        assertTrue(notifications.get("test@test.com").get(0).contains("Recently there has been a lot of noise"));
    }

    @Test
    void testGetAllPostsForGroup() throws InterruptedException {
        Scraper scraper = new Scraper();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/GroupPageOld.html";

        List<String> posts = scraper.getAllPostsForGroup(htmlLocation, yesterday);
        assertEquals(15, posts.size());

        assertEquals(posts.get(0).substring(0, 17), "PSA: One thing I ");
        assertEquals(posts.get(10).substring(0, 14), "My new article");
    }

    @Test
    void startDriverTest() {
        Scraper scraper = new Scraper();
        WebDriver driver = scraper.startDriver();
        driver.close();
    }
}