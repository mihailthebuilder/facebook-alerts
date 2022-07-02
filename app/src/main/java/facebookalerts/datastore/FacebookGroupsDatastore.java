package facebookalerts.datastore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import facebookalerts.records.FacebookGroupRecord;

public class FacebookGroupsDatastore {

    public FacebookGroupRecord[] getAllFacebookGroups()
            throws FileNotFoundException, IOException, ClassNotFoundException {

        ObjectMapper objectMapper = new ObjectMapper();

        FacebookGroupRecord[] facebookGroupRecords = objectMapper.readValue(getDbFile(), FacebookGroupRecord[].class);

        return facebookGroupRecords;
    }

    protected File getDbFile() {
        String dbFilePath = new String(
                "/home/mmarian/dev/facebook-alerts/app/src/main/resources/db/FacebookGroups.json");

        return new File(dbFilePath);
    }
}