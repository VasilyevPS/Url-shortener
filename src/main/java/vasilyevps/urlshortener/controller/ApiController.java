package vasilyevps.urlshortener.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.dto.UrlDto;
import vasilyevps.urlshortener.service.UrlService;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ApiController {

    public static final String URL_KEY = "/{urlKey}";

    private final UrlService urlService;

    @Operation(summary = "Create new short link")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201"),
        @ApiResponse(responseCode = "422",content = @Content(schema = @Schema(hidden = true)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlDto create(@RequestBody @Valid final UrlCreateDto urlCreateDto) {
        return urlService.create(urlCreateDto);
    }

    @Operation(summary = "Get original link from short link")
    @GetMapping(URL_KEY)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", content = @Content(schema = @Schema(hidden = true))),
        @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<String> getLongUrl(@PathVariable String urlKey) {
        String longUrl = urlService.getUrlByUrkKey(urlKey);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", longUrl).body("");
    }

    @Operation(summary = "Delete short link")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200"),
        @ApiResponse(responseCode = "404")
    })
    @DeleteMapping(URL_KEY)
    public void delete(@PathVariable String urlKey) {
        urlService.deleteUrl(urlKey);
    }
}
