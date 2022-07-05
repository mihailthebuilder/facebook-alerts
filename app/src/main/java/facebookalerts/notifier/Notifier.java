package facebookalerts.notifier;

import java.util.List;
import java.util.Map;

import facebookalerts.records.KeywordRecord;

public class Notifier {

    private Map<String, List<String>> notifications;

    protected Map<String, List<String>> getNotifications() {
        return this.notifications;
    }

    protected void setNotifications(Map<String, List<String>> notifications) {
        this.notifications = notifications;
    }

    public void addNotificationsForGroup(List<String> posts, List<KeywordRecord> keywords) {
    }
}
