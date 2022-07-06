package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ScraperTest {

    @Test
    void testGetAllPostsForGroup() throws InterruptedException {
        Scraper scraper = new Scraper();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/GroupPage.html";

        List<String> posts = scraper.getAllPostsForGroup(htmlLocation, yesterday);
        assertEquals(15, posts.size());

        assertEquals(posts.get(0).substring(0, 17), "PSA: One thing I ");
        assertEquals(posts.get(10).substring(0, 14), "My new article");

        scraper.close();
    }

    @Test
    void testScraperStartAndClose() {
        Scraper scraper = new Scraper();
        scraper.close();
    }
}