package facebookalerts.records;

import java.util.List;

public record KeywordRecord(
        String keyword,
        List<String> subscribedEmailAddresses) {
}
