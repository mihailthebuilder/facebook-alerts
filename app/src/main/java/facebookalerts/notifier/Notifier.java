package facebookalerts.notifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.records.KeywordRecord;

public class Notifier {

    private Map<String, List<String>> notifications = new HashMap<String, List<String>>();
    private BrowserDriver browserDriver;

    public Notifier(BrowserDriver driver) {
        this.browserDriver = driver;
    }

    public List<String> getNotificationsForUser(String userEmail) {
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
        this.browserDriver.goToWebsite("https://www.messenger.com");

        for (String user : this.notifications.keySet()) {
            this.browserDriver.findElementByCssSelector("[aria-label=\"Search Messenger\"]").sendKeys(user);
            this.browserDriver.findElementByCssSelector("li[role=\"option\"]:nth-of-type(2)").click();

            List<String> posts = this.notifications.get(user);
            for (String post : posts) {
                this.browserDriver.findElementByCssSelector("[aria-label=\"Message\"]").sendKeys(post);
            }
            this.browserDriver.findElementByCssSelector("[aria-label=\"Press enter to send\"]").click();
        }

        System.out.println(this.notifications);
    }
}
