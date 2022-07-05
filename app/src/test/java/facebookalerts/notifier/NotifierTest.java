package facebookalerts.notifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import facebookalerts.records.KeywordRecord;

public class NotifierTest {
    @Test
    void testAddNotificationsForGroup() {

    }

    @Test
    void testProcessPostsIntoNotifications() {

        Notifier notifier = new Notifier();

        List<String> posts = Arrays.asList("hello world", "how are you this morning", "hello it's a new day");

        KeywordRecord keyword1 = new KeywordRecord("hello",
                Arrays.asList(new String[] { "test@test.com", "one@one.com" }));
        KeywordRecord keyword2 = new KeywordRecord("how",
                Arrays.asList(new String[] { "test@test.com" }));
        KeywordRecord keyword3 = new KeywordRecord("nothing",
                Arrays.asList(new String[] { "one@one.com", "two@two.com" }));
        List<KeywordRecord> keywords = Arrays.asList(keyword1, keyword2, keyword3);

        notifier.processPostsIntoNotifications(posts, keywords);

        assertEquals(Arrays.asList("hello world", "how are you this morning"),
                notifier.getNotificationsForUser("test@test.com"));

        assertEquals(Arrays.asList("hello world"), notifier.getNotificationsForUser("one@one.com"));

        assertEquals(null, notifier.getNotificationsForUser("two@two.com"));
    }

    @Test
    void testSendNotifications() {

    }
}
