package com.pods.fclabs.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
@ApiIgnore
public class RedirectController {

    @RequestMapping(value = "/swagger", method = RequestMethod.GET)
    public RedirectView swaggerRoute() {
        return new RedirectView("/swagger-ui.html#");
    }

    @RequestMapping(method = RequestMethod.GET)
    public RedirectView redirectToSwagger() {
        return new RedirectView("/swagger-ui.html#");
    }
}

