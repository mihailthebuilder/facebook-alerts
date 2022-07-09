package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notifier.Notifier;
import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.scraper.Scraper;

public class Bootstrapper {
    protected Scraper scraper = new Scraper();
    protected FacebookGroupsDatastore datastore = new FacebookGroupsDatastore();
    protected Notifier notifier = new Notifier();

    public void run() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        List<FacebookGroupRecord> facebookGroups = this.datastore.getAllFacebookGroups();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);

        this.scraper.start();

        for (FacebookGroupRecord group : facebookGroups) {

            String groupSite = String.format("https://www.facebook.com/groups/%s", group.facebookUrlId());
            List<String> posts = this.scraper.getAllPostsForGroup(groupSite, yesterday);

            this.notifier.findRelevantPostsAndAddToNotificationsQueue(posts, group.keywords());
        }

        this.scraper.close();
        this.notifier.sendNotifications();
    }
}