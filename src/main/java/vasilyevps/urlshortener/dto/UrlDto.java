package vasilyevps.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlDto {
    private String key;
    private String shortUrl;
    private String longUrl;
    @JsonIgnore
    private Date createdAt;
    @JsonIgnore
    private int visitsCount;
}
