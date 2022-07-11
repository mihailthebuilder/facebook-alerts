package facebookalerts.notifier;

import java.util.Iterator;
import java.util.List;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.notificationsqueue.NotificationsQueue;

public class Notifier {

    private NotificationsQueue queue;
    private BrowserDriver browserDriver;

    public Notifier(BrowserDriver driver, NotificationsQueue queue) {
        this.browserDriver = driver;
        this.queue = queue;
    }

    public void sendNotifications() {
        this.browserDriver.goToWebsite("https://www.messenger.com");

        Iterator<String> usersIterator = this.queue.getAllUsers().iterator();

        while (usersIterator.hasNext()) {
            String user = usersIterator.next();
            this.browserDriver.findElementByCssSelector("[aria-label=\"Search Messenger\"]").sendKeys(user);
            this.browserDriver.findElementByCssSelector("li[role=\"option\"]:nth-of-type(2)").click();

            List<String> posts = this.queue.getNotificationsForUser(user);

            this.browserDriver.findElementByCssSelector("[aria-label=\"Message\"]").click();

            for (String post : posts) {
                this.browserDriver.findElementByCssSelector("[aria-label=\"Message\"]").sendKeys(post);
            }
            this.browserDriver.findElementByCssSelector("[aria-label=\"Press enter to send\"]").click();
        }

    }
}
