package vasilyevps.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlDto {
    private String key;
    private String shortUrl;
    private String longUrl;
}
