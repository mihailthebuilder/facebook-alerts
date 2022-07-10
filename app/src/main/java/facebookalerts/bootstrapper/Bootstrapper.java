package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notifier.Notifier;
import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.scraper.Scraper;

public class Bootstrapper {
    protected BrowserDriver browserDriver = new BrowserDriver();
    protected FacebookGroupsDatastore datastore = new FacebookGroupsDatastore();
    protected Notifier notifier = new Notifier();

    public void run() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        List<FacebookGroupRecord> facebookGroups = this.datastore.getAllFacebookGroups();

        this.browserDriver.start();

        Scraper scraper = new Scraper(this.browserDriver);

        for (FacebookGroupRecord group : facebookGroups) {

            String groupUrl = String.format("https://www.facebook.com/groups/%s", group.facebookUrlId());
            browserDriver.goToWebsite(groupUrl);

            List<String> posts = scraper.getAllPostsForGroup();

            this.notifier.findRelevantPostsAndAddToNotificationsQueue(posts,
                    group.keywords());
        }

        this.browserDriver.close();
        this.notifier.sendNotifications();
    }
}
