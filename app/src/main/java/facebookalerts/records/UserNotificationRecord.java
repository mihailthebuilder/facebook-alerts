package facebookalerts.records;

import java.util.List;

public record UserNotificationRecord(
        String emailAddress,
        List<String> posts) {
}
