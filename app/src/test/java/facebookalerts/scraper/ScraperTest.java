package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/FacebookGroupPage.html";

        FacebookGroupRecord group = new FacebookGroupRecord(htmlLocation, "yesterday",
                Arrays.asList(new KeywordRecord[] { keyword }));

        Map<String, List<String>> notifications = scraper.getUserNotifications(group, yesterday);

        assertTrue(notifications.get("test@test.com").size() > 0);

        assertTrue(notifications.get("test@test.com").get(0).contains("Recently there has been a lot of noise"));
    }

    @Test
    void startDriverTest() {
        Scraper scraper = new Scraper();
        WebDriver driver = scraper.startDriver();
        driver.close();
    }

    @Test
    void testCreatePostCssSelector() throws IOException {
        Scraper scraper = new Scraper();

        File input = new File("/home/mmarian/dev/facebook-alerts/app/src/test/resources/Post.html");

        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        String selector = scraper.createPostCssSelector(1);

        Elements results = doc.select(selector);

        assertNotNull(results.first());
    }
}