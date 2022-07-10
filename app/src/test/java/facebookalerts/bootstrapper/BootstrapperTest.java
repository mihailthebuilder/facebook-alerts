package facebookalerts.bootstrapper;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class BootstrapperTest {
    @Test
    void testRun() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
        Bootstrapper bootstrapper = new Bootstrapper();
        bootstrapper.run();
    }
}
