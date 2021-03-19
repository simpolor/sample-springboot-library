package io.simpolor.json.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping({"/", "/index", "/welcome"})
    public String welcome() {
        return "Welcome to Simpolor World";
    }

}
