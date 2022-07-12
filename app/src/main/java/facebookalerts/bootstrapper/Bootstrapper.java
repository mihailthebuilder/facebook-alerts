package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notificationsqueue.NotificationsQueue;
import facebookalerts.notifier.Notifier;
import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.scraper.Scraper;

public class Bootstrapper {
    private Scraper scraper;
    private FacebookGroupsDatastore datastore;
    private Notifier notifier;
    private BrowserDriver browserDriver;

    public Bootstrapper(Scraper scraper, FacebookGroupsDatastore datastore, Notifier notifier,
            BrowserDriver browserDriver) {
        this.scraper = scraper;
        this.datastore = datastore;
        this.notifier = notifier;
        this.browserDriver = browserDriver;
    }

    public void run() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        List<FacebookGroupRecord> facebookGroups = this.datastore.getAllFacebookGroups();

        NotificationsQueue notificationsQueue = new NotificationsQueue();

        for (FacebookGroupRecord group : facebookGroups) {

            String groupUrl = String.format("https://www.facebook.com/groups/%s", group.facebookUrlId());
            browserDriver.goToWebsite(groupUrl);

            List<String> posts = scraper.getAllPostsForGroup();

            notificationsQueue.findRelevantPostsAndAddToNotificationsQueue(posts,
                    group.keywords());
        }

        this.notifier.sendNotifications(notificationsQueue);
    }
}
