package utils;
import java.util.UUID;

public class TestDataUtils {
    public static String generateUniqueEmail(String email) {
        String[] emailPart = email.split("@");
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return emailPart[0] + "_" + uuid + "@" + emailPart[1];
    }
}