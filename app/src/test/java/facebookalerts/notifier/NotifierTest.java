package facebookalerts.notifier;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.notificationsqueue.NotificationsQueue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NotifierTest {

        private BrowserDriver driver;

        @BeforeAll
        public void setup() {
                this.driver = new BrowserDriver();
                this.driver.start();
        }

        @AfterAll
        public void teardown() {
                this.driver.close();
        }

        @Test
        public void testSendNotifications() {
                NotificationsQueue queue = new NotificationsQueue();
                queue.addNotificationToUser("Mihail Marian", "hello world");
                queue.addNotificationToUser("Mihail Marian", "how are you");

                Notifier notifier = new Notifier(this.driver, queue);
                notifier.sendNotifications();
                driver.close();
        }
}
