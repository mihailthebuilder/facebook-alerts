package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import facebookalerts.browserdriver.BrowserDriver;
import facebookalerts.datastore.FacebookGroupsDatastore;
import facebookalerts.notifier.Notifier;
import facebookalerts.scraper.Scraper;

public class BootstrapperTest {

    @Mock
    private Scraper scraper;

    @Mock
    private BrowserDriver browserDriver;

    @Mock
    private FacebookGroupsDatastore datastore;

    @Mock
    private Notifier notifier;

    @Test
    void testRun() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        Bootstrapper bootstrapper = new Bootstrapper(scraper, datastore, notifier, browserDriver);
        bootstrapper.run();
    }
}
