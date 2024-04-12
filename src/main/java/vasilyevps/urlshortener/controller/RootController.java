package vasilyevps.urlshortener.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("${base-url}")
public class RootController {

    private final UrlService urlService;

    @GetMapping
    public String welcome() {
        return "This page is under construction";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UrlDto create(@RequestBody @Valid final UrlCreateDto urlCreateDto) {
        return urlService.create(urlCreateDto);
    }
}
