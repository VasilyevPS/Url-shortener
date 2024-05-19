package vasilyevps.urlshortener.controller;


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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlDto create(@RequestBody @Valid final UrlCreateDto urlCreateDto) {
        return urlService.create(urlCreateDto);
    }

    @GetMapping(URL_KEY)
    public ResponseEntity<String> getLongUrl(@PathVariable String urlKey) {
        String longUrl = urlService.getUrlByUrkKey(urlKey);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", longUrl).body("");
    }

    @DeleteMapping(URL_KEY)
    public void delete(@PathVariable String urlKey) {
        urlService.deleteUrl(urlKey);
    }
}
