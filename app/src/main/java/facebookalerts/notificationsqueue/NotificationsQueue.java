package facebookalerts.notificationsqueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import facebookalerts.records.KeywordRecord;

public class NotificationsQueue {

    private Map<String, List<String>> queue = new HashMap<String, List<String>>();

    public List<String> getNotificationsForUser(String userEmail) {
        return this.queue.get(userEmail);
    }

    public Set<String> getAllUsers() {
        return this.queue.keySet();
    }

    public void addNotificationToUser(String userEmail, String post) {

        if (this.queue.get(userEmail) == null) {
            this.queue.put(userEmail, new ArrayList<String>(Arrays.asList(post)));
            return;
        }

        this.queue.get(userEmail).add(post);
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
}
