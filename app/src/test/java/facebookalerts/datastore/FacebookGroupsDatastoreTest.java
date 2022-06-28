package facebookalerts.datastore;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FacebookGroupsDatastoreTest {
    @Test
    void testGetAllFacebookGroups() throws FileNotFoundException, ClassNotFoundException, IOException {
        FacebookGroupsDatastore datastore = new FacebookGroupsDatastore();

        FacebookGroupRecord[] records = datastore.getAllFacebookGroups();

        System.out.println(records[0].facebookUrlId());

        assertNotNull(records, "should return results");
    }
}
