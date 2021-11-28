package com.robotech.magellan.moviemanager.services;

import com.robotech.magellan.moviemanager.DTOs.DirectorDTO;
import com.robotech.magellan.moviemanager.DTOs.MovieDTO;
import com.robotech.magellan.moviemanager.models.Director;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.models.Rating;
import com.robotech.magellan.moviemanager.repositories.DirectorRepository;
import com.robotech.magellan.moviemanager.repositories.MovieRepository;
import com.robotech.magellan.moviemanager.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private RatingRepository ratingRepository;

    /*CRUD*/
    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovie(Long id){
        return movieRepository.findById(id).get();
    }

    public Movie createMovie(MovieDTO movieDTO){
        return movieRepository.save(
                new Movie(
                        movieDTO.name,
                        movieDTO.ratingsList,
                        movieDTO.director));
    }

    public Movie updateMovie(Long id, MovieDTO movieDTO){
        Movie movie = movieRepository.findById(id).get();
        movie.setName(movieDTO.name);
        movie.setRatings(movieDTO.ratingsList);
        movie.setDirector(movieDTO.director);
        return movieRepository.save(movie);
    }

    public Movie addRatingToMovie(Long id, Rating rating){
        Movie movie = movieRepository.findById(id).get();
        List<Rating> ratingsUpdatedList = movie.getRatings();
        ratingsUpdatedList.add(rating);
        movie.setRatings(ratingsUpdatedList);
        return  movieRepository.save(movie);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */

    public List<Movie> findByDirector(String directorName){
        return movieRepository.findByDirectorName(directorName);
    }

    public List<Movie> findMoviesWithRatingHigerThan(Long rating){
        List<Movie> movies = new ArrayList<>();
        ratingRepository
                .findByRatingValueGreaterThan(rating)
                .forEach(r -> movies.add(r.getMovie()));
        return movies;
    }
}
