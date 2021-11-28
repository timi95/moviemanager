package com.robotech.magellan.moviemanager.controllers;



import com.robotech.magellan.moviemanager.DTOs.DirectorDTO;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.services.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("HollyWoo")
public class HollyWooController {
    @Autowired
    MovieService movieService;

    private static final Logger L = LogManager.getLogger(HollyWooController.class);

    @GetMapping(path="hello-world")
    public String HelloWorld(   ) {
        return "Hello world !";
    }


    @GetMapping(path = "search-by-director")
    public List<Movie> searcMoviesByDirector(@RequestParam String directorName){
        return movieService.findByDirector(directorName);
    }

    @GetMapping(path = "rating-higher-than")
    public List<Movie> searchMoviesWithRatingsHigherThan(@RequestParam Long rating){
        return movieService.findMoviesWithRatingHigerThan(rating);
    }
}
