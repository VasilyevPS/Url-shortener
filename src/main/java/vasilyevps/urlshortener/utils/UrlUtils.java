package vasilyevps.urlshortener.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlUtils {
    static final int KEY_LENGTH = 10;
    @Value("${base-url}")
    String baseUrl;

    public String generateUrlKey(String longUrl) {
        String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(longUrl);
        return sha3Hex.substring(0, KEY_LENGTH);
    }

    public String normalizeUrl(String longUrl) {
        String normalizedUrl = longUrl.split("\\?")[0];
        if (normalizedUrl.charAt(normalizedUrl.length() - 1) != '/') {
            normalizedUrl += '/';
        }
        return normalizedUrl.toLowerCase();
    }

    public String createShortUrl(String urlKey) {
        String port = System.getenv().getOrDefault("PORT", "5001");

        return "localhost:" + port + baseUrl + "/" + urlKey + "/";
    }
}
