package facebookalerts.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FacebookGroupsDatastoreTest {
    @Test
    void testGetAllFacebookGroups() throws FileNotFoundException, ClassNotFoundException, IOException {
        FacebookGroupsDatastore datastore = new FacebookGroupsDatastore();

        List<FacebookGroupRecord> recordList = List.of(datastore.getAllFacebookGroups());

        assertEquals(recordList.size(), 1);

        FacebookGroupRecord record = recordList.get(0);
        assertEquals(record.facebookUrlId(), "581688222758774");

        List<KeywordRecord> keywordList = List.of(record.keywords());
        assertEquals(keywordList.size(), 1);
        assertEquals(keywordList.get(0).keyword(), "free");
    }
}
