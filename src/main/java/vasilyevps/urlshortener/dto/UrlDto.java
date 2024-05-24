package vasilyevps.urlshortener.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "URL")
public class UrlDto {
    @Schema(example = "0cdf9850c3")
    private String key;
    @Schema(example = "http://localhost:8080/0cdf9850c3")
    private String shortUrl;
    @Schema(example = "https://www.example.com")
    private String longUrl;
    @JsonIgnore
    private Date createdAt;
    @JsonIgnore
    private int visitsCount;
}
