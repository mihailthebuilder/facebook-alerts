package facebookalerts.bootstrapper;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notificationsqueue.NotificationsQueue;
import facebookalerts.notifier.Notifier;
import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.records.KeywordRecord;
import facebookalerts.scraper.Scraper;

public class BootstrapperTest {

    @Mock
    private Scraper scraper;

    @Mock
    private BrowserDriver browserDriver;

    @Mock
    private FacebookGroupsDatastore datastore;

    @Mock
    private Notifier notifier;

    @Test
    void testRun() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {

        // GIVEN
        KeywordRecord keyword1 = new KeywordRecord("keytest", new ArrayList<String>(Arrays.asList("user1")));
        KeywordRecord keyword2 = new KeywordRecord("nokey", new ArrayList<String>(Arrays.asList("user2")));

        FacebookGroupRecord group = new FacebookGroupRecord("fbGroup",
                new ArrayList<KeywordRecord>(Arrays.asList(keyword1, keyword2)));

        browserDriver = new BrowserDriver();
        datastore = new FacebookGroupsDatastore();
        notifier = new Notifier(browserDriver);
        scraper = new Scraper(browserDriver);

        when(datastore.getAllFacebookGroups()).thenReturn(new ArrayList<FacebookGroupRecord>(Arrays.asList(group)));

        doNothing().when(browserDriver).goToWebsite("https://www.facebook.com/groups/fbGroup");
        when(scraper.getAllPostsForGroup())
                .thenReturn(new ArrayList<String>(Arrays.asList("one keytest", "two")));

        NotificationsQueue queue = new NotificationsQueue();
        queue.addNotificationToUser("user1", "one keytest");
        doNothing().when(notifier).sendNotifications(queue);

        // WHEN
        Bootstrapper bootstrapper = new Bootstrapper(scraper, datastore, notifier, browserDriver);
        bootstrapper.run();

        // THEN
        // assertions are made in GIVEN
    }
}
