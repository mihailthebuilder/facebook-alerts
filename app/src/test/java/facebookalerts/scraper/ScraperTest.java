package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import facebookalerts.browserdriver.BrowserDriver;

public class ScraperTest {

    @Test
    void testGetAllPostsForGroup() throws InterruptedException {
        BrowserDriver driver = new BrowserDriver();
        driver.start();

        Scraper scraper = new Scraper(driver);

        String htmlLocation = "file:///home/mmarian/dev/facebook-alerts/app/src/test/resources/GroupPage.html";
        driver.goToWebsite(htmlLocation);

        List<String> posts = scraper.getAllPostsForGroup();
        driver.close();

        assertEquals(15, posts.size());

        assertEquals(posts.get(0).substring(0, 17), "PSA: One thing I ");
        assertEquals(posts.get(10).substring(0, 14), "My new article");

    }
}