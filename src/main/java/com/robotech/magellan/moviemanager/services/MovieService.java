package com.robotech.magellan.moviemanager.services;

import com.robotech.magellan.moviemanager.DTOs.DirectorDTO;
import com.robotech.magellan.moviemanager.DTOs.MovieDTO;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.models.Rating;
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
        return movieRepository.findAll();
    }

    private Movie findMovie(Long id){
        return movieRepository.findById(id).get();
    }

    private Movie createMovie(MovieDTO movieDTO){
        return movieRepository.save(
                new Movie(
                        movieDTO.name,
                        movieDTO.ratingsList,
                        movieDTO.director));
    }

    private Movie updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id).get();
        movie.setName(movieDTO.name);
        movie.setRatings(movieDTO.ratingsList);
        movie.setDirector(movieDTO.director);
        return movieRepository.save(movie);
    }

    private Movie addRatingToMovie(Long id, Rating rating){
        Movie movie = movieRepository.findById(id).get();
        List<Rating> ratingsUpdatedList = movie.getRatings();
        ratingsUpdatedList.add(rating);
        movie.setRatings(ratingsUpdatedList);
        return  movieRepository.save(movie);
    }

    private void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */

    private List<Movie> findByDirector(DirectorDTO directorDTO){
        return null;
    }

    private List<Movie> findMoviesAboveRating(Long rating){
        return null;
    }
}
