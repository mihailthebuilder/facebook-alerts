package facebookalerts.notificationsqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import facebookalerts.records.KeywordRecord;

public class NotificationsQueueTest {

    @Test
    void testFindNotificationsAndAddToQueue() {

        KeywordRecord keyword1 = new KeywordRecord("hello",
                Arrays.asList(new String[] { "test@test.com", "one@one.com" }));
        KeywordRecord keyword2 = new KeywordRecord("how",
                Arrays.asList(new String[] { "test@test.com" }));
        KeywordRecord keyword3 = new KeywordRecord("nothing",
                Arrays.asList(new String[] { "one@one.com", "two@two.com" }));
        List<KeywordRecord> keywords = Arrays.asList(keyword1, keyword2, keyword3);
        List<String> posts = Arrays.asList("hello world", "how are you this morning", "hello it's a new day");

        NotificationsQueue queue = new NotificationsQueue();
        queue.findRelevantPostsAndAddToNotificationsQueue(posts, keywords);

        assertEquals(Arrays.asList("hello world", "hello it's a new day", "how are you this morning"),
                queue.getNotificationsForUser("test@test.com"));

        assertEquals(Arrays.asList("hello world", "hello it's a new day"),
                queue.getNotificationsForUser("one@one.com"));

        assertEquals(null, queue.getNotificationsForUser("two@two.com"));
    }

    @Test
    void testAddNotificationToUser() {
        NotificationsQueue queue = new NotificationsQueue();

        queue.addNotificationToUser("test@test.com", "hello world");
        queue.addNotificationToUser("test@test.com", "how are you");

        assertEquals(Arrays.asList("hello world", "how are you"),
                queue.getNotificationsForUser("test@test.com"));
    }
}
