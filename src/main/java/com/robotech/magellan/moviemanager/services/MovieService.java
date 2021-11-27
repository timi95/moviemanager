package com.robotech.magellan.moviemanager.services;

import com.robotech.magellan.moviemanager.DTOs.DirectorDTO;
import com.robotech.magellan.moviemanager.DTOs.MovieDTO;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    /*CRUD*/
    private List<Movie> getMovies(){
        return null;
    }

    private Movie findMovie(Integer id){
        return null;
    }

    private Movie createMovie(MovieDTO movieDTO){
        return null;
    }

    private Movie updateMovie(MovieDTO movieDTO){
        return null;
    }

    private void deleteMovie(Integer id){
    }

    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */

    private List<Movie> findByDirector(DirectorDTO directorDTO){
        return null;
    }

    private List<Movie> findMoviesAboveRating(Integer rating){
        return null;
    }
}
