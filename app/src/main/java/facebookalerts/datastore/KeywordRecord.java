package facebookalerts.datastore;

public record KeywordRecord(
        String keyword,
        String[] subscribedEmailAddresses) {
}
