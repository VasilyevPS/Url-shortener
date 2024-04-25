package vasilyevps.urlshortener.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import vasilyevps.urlshortener.dto.UrlCreateDto;
import vasilyevps.urlshortener.dto.UrlDto;
import vasilyevps.urlshortener.service.UrlService;

@AllArgsConstructor
@Controller
@RequestMapping
public class UrlController {

    public static final String URL_KEY = "/{urlKey}";

    private final UrlService urlService;

    @GetMapping
    public String welcome() {
        return "index";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView create(@Valid final UrlCreateDto urlCreateDto) {
        ModelAndView model = new ModelAndView("index");
        UrlDto urlDto = urlService.create(urlCreateDto);
        model.addObject(urlDto);
        model.addObject("message", "success");
        return model;
    }

    @PostMapping("/api")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UrlDto createAPI(@RequestBody @Valid final UrlCreateDto urlCreateDto) {
        return urlService.create(urlCreateDto);
    }

    @GetMapping(URL_KEY)
    @ResponseStatus(HttpStatus.FOUND)
    public ModelAndView getLongUrl(@PathVariable String urlKey) {
        String url = urlService.getUrlByUrkKey(urlKey);
        return new ModelAndView("redirect:" + url);
    }

    @DeleteMapping(URL_KEY)
    @ResponseBody
    public void delete(@PathVariable String urlKey) {
        urlService.deleteUrl(urlKey);
    }

}
