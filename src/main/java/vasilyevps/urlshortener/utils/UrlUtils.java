package vasilyevps.urlshortener.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class UrlUtils {
    static final int KEY_LENGTH = 10;

    public String generateUrlKey(String longUrl) {
        String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(longUrl);
        return sha3Hex.substring(0, KEY_LENGTH);
    }

    public String normalizeUrl(String longUrl) {
        String normalizedUrl = longUrl.split("\\?")[0];
        int length = normalizedUrl.length();
        if (normalizedUrl.charAt(length - 1) == '/') {
            normalizedUrl = normalizedUrl.substring(0, length - 1);
        }
        return normalizedUrl.toLowerCase();
    }

    public String createShortUrl(String urlKey) {
        String port = System.getenv().getOrDefault("PORT", "8080");
        return "http://localhost:" + port + "/" + urlKey;
    }
}
