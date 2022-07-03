package facebookalerts.records;

import java.io.Serializable;
import java.util.List;

public record FacebookGroupRecord(
        String facebookUrlId,
        String lastUpdateTime,
        List<KeywordRecord> keywords) implements Serializable {
}
