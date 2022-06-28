package facebookalerts.datastore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FacebookGroupsDatastore {
    private String dbLocation = "/home/mmarian/dev/facebook-alerts/app/src/main/resources/db/FacebookGroups.json";

    public FacebookGroupRecord[] getAllFacebookGroups()
            throws FileNotFoundException, IOException, ClassNotFoundException {

        File f = new File(dbLocation);
        ObjectMapper objectMapper = new ObjectMapper();

        FacebookGroupRecord[] facebookGroupRecords = objectMapper.readValue(f, FacebookGroupRecord[].class);

        return facebookGroupRecords;
    }
}