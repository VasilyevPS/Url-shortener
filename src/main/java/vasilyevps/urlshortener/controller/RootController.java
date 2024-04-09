package vasilyevps.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("")
    public String welcome() {
        return "This page is under construction";
    }
}
