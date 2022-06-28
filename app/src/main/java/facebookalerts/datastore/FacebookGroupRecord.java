package facebookalerts.datastore;

import java.io.Serializable;

public record FacebookGroupRecord(
        String facebookUrlId,
        String lastUpdateTime,
        UserRecord[] users) implements Serializable {
}
