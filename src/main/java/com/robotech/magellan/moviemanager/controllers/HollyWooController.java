package com.robotech.magellan.moviemanager.controllers;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("Movies")
public class HollyWooController {

    private static final Logger L = LogManager.getLogger(HollyWooController.class);

    @GetMapping(path="hello-world")
    public String HelloWorld(   ) {
        return "Hello world !";
    }
}
