package facebookalerts.notifier;

import java.util.Iterator;
import java.util.List;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.notificationsqueue.NotificationsQueue;

public class Notifier {

    private BrowserDriver browserDriver;

    public Notifier(BrowserDriver driver) {
        this.browserDriver = driver;
    }

    public void sendNotifications(NotificationsQueue queue) {
        this.browserDriver.goToWebsite("https://www.messenger.com");

        Iterator<String> usersIterator = queue.getAllUsers().iterator();

        while (usersIterator.hasNext()) {
            String user = usersIterator.next();
            this.browserDriver.findElementByCssSelector("[aria-label=\"Search Messenger\"]").sendKeys(user);
            this.browserDriver.findElementByCssSelector("li[role=\"option\"]:nth-of-type(2)").click();

            this.browserDriver.findElementByCssSelector("[aria-label=\"Message\"]").click();

            List<String> posts = queue.getNotificationsForUser(user);
            String message = String.join(" | ", posts);
            this.browserDriver.findElementByCssSelector("[aria-label=\"Message\"]").sendKeys(message);

            this.browserDriver.findElementByCssSelector("[aria-label=\"Press enter to send\"]").click();
        }

    }
}
