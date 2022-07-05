package facebookalerts.notifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import facebookalerts.records.KeywordRecord;

public class Notifier {

    private Map<String, List<String>> notifications = new HashMap<String, List<String>>();

    protected List<String> getNotificationsForUser(String userEmail) {
        return this.notifications.get(userEmail);
    }

    protected void addNotificationToUser(String userEmail, List<String> posts) {
        this.getNotificationsForUser(userEmail).addAll(posts);
    }

    public void processPostsIntoNotifications(List<String> posts, List<KeywordRecord> keywords) {

    }

    public void sendNotifications() {
    }
}
