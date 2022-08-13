package facebookalerts.records;

import java.io.Serializable;
import java.util.List;

public record FacebookGroupRecord(
                String facebookUrlId,
                List<KeywordRecord> keywords) implements Serializable {
}
