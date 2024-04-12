package vasilyevps.urlshortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto {
    private String key;
    private String shortUrl;
    private String longUrl;
}
