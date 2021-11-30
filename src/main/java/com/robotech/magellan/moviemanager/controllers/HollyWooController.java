package com.robotech.magellan.moviemanager.controllers;



import com.robotech.magellan.moviemanager.DTOs.DirectorDTO;
import com.robotech.magellan.moviemanager.DTOs.MovieDTO;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.services.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("HollyWoo")
public class HollyWooController {
    @Autowired
    MovieService movieService;

    private static final Logger L = LogManager.getLogger(HollyWooController.class);

    @GetMapping(path="hello-world")
    public String HelloWorld(   ) {
        return "Hello world !";
    }

    /*CRUD*/
    @PostMapping(path = "movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @GetMapping(path = "movie")
    public ResponseEntity<List<Movie>> getMovies(){
        return  movieService.getMovies();
    }
    @GetMapping(path = "movie/{id}")
    public ResponseEntity<Movie> findMovie(@PathVariable Long id){
        return  movieService.findMovie(id);
    }

    @PutMapping(path = "movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return  movieService.updateMovie(id, movie);
    }

    @DeleteMapping(path = "movie/{id}")
    public void deleteMovie(@PathVariable Long id){
         movieService.deleteMovie(id);
    }


    @GetMapping(path = "search-by-director/{directorName}")
    public ResponseEntity<List<Movie>> searcMoviesByDirector(@PathVariable String directorName){
        return movieService.findByDirector(directorName);
    }

    @GetMapping(path = "rating-higher-than/{rating}")
    public ResponseEntity<List<Movie>> searchMoviesWithRatingsHigherThan(@PathVariable Long rating){
        return movieService.findMoviesWithRatingHigerThan(rating);
    }
}
