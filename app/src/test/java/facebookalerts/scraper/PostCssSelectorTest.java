package facebookalerts.scraper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class PostCssSelectorTest {

    private Document document;
    private PostCssSelector selector;

    public PostCssSelectorTest() throws IOException {
        File input = new File("/home/mmarian/dev/facebook-alerts/app/src/test/resources/Post.html");
        Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
        this.document = document;
        this.selector = new PostCssSelector();
    }

    @Test
    void selectorFindsFirstPost() throws IOException {
        String query = this.selector.createPostCssSelector(1);
        Elements results = this.document.select(query);
        assertNotNull(results.first());
    }

    @Test
    void selectorFindsSecondPost() throws IOException {
        String query = this.selector.createPostCssSelector(2);
        Elements results = this.document.select(query);
        assertNotNull(results.first());
    }

    @Test
    void selectorDoesntFindThirdPost() throws IOException {
        String query = this.selector.createPostCssSelector(3);
        Elements results = this.document.select(query);
        assertNull(results.first());
    }
}
