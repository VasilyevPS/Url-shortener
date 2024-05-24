package vasilyevps.urlshortener.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "URL")
public class UrlCreateDto {
    @Schema(description = "The field should not be empty", example = "https://www.example.com")
    @NotBlank
    private String url;
}
