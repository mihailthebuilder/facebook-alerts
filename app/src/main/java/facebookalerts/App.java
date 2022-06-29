/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package facebookalerts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import facebookalerts.records.FacebookGroupRecord;
import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notifier.Notifier;
import facebookalerts.records.UserNotificationRecord;
import facebookalerts.scraper.Scraper;

public class App {

    public static void main(String[] args)
            throws InterruptedException, FileNotFoundException, ClassNotFoundException, IOException {

        FacebookGroupsDatastore datastore = new FacebookGroupsDatastore();
        FacebookGroupRecord[] facebookGroupList = datastore.getAllFacebookGroups();

        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);

        Scraper scraper = new Scraper();
        Notifier notifier = new Notifier();

        for (FacebookGroupRecord facebookGroup : facebookGroupList) {
            UserNotificationRecord[] notificationList = scraper.getUserNotifications(facebookGroup, yesterday);

            for (UserNotificationRecord notification : notificationList) {
                notifier.sendNotification(notification);
            }
        }
    }
}