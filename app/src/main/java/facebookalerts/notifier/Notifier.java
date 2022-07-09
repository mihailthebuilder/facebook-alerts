package facebookalerts.notifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import facebookalerts.records.KeywordRecord;

public class Notifier {

    private Map<String, List<String>> notifications = new HashMap<String, List<String>>();

    protected List<String> getNotificationsForUser(String userEmail) {
        return this.notifications.get(userEmail);
    }

    protected void addNotificationToUser(String userEmail, String post) {

        if (this.notifications.get(userEmail) == null) {
            this.notifications.put(userEmail, new ArrayList<String>(Arrays.asList(post)));
            return;
        }

        this.notifications.get(userEmail).add(post);
    }

    public void findRelevantPostsAndAddToNotificationsQueue(List<String> posts, List<KeywordRecord> keywords) {
        for (KeywordRecord keyword : keywords) {
            for (String post : posts) {
                if (post.contains(keyword.keyword())) {
                    for (String email : keyword.subscribedEmailAddresses()) {
                        this.addNotificationToUser(email, post);
                    }
                }
            }
        }
    }

    public void sendNotifications() {
        System.out.println(this.notifications);
    }
}
