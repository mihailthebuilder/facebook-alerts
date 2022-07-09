package facebookalerts.bootstrapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class BootstrapperTest {
    @Test
    void testRun() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.run();
        List<String> userNotifications = bootstrapper.notifier.getNotificationsForUser("hidden@hidden.com");
        assertNotNull(userNotifications);
    }
}
