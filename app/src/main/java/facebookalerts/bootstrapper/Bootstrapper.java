package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;
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

        this.scraper.start();

        for (FacebookGroupRecord group : facebookGroups) {

            String groupUrl = String.format("https://www.facebook.com/groups/%s", group.facebookUrlId());
            this.scraper.goToGroupSite(groupUrl);

            List<String> posts = this.scraper.getAllPostsForGroup();

            this.notifier.findRelevantPostsAndAddToNotificationsQueue(posts,
                    group.keywords());
        }

        this.scraper.close();
        this.notifier.sendNotifications();
    }
}
