package facebookalerts.datastore;

public record UserRecord(
        String emailAddress,
        String[] keywords) {
}
