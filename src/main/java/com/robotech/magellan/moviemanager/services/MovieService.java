package com.robotech.magellan.moviemanager.services;

import com.robotech.magellan.moviemanager.DTOs.MovieDTO;
import com.robotech.magellan.moviemanager.models.Director;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.models.Rating;
import com.robotech.magellan.moviemanager.repositories.DirectorRepository;
import com.robotech.magellan.moviemanager.repositories.MovieRepository;
import com.robotech.magellan.moviemanager.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private RatingRepository ratingRepository;

    /*CRUD*/
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity(movieRepository.findAll(), HttpStatus.MULTI_STATUS);
    }

    public ResponseEntity<Movie> findMovie(Long id){
        return new ResponseEntity(
                movieRepository
                        .findById(id)
                        .orElseThrow(EntityNotFoundException::new),
                HttpStatus.MULTI_STATUS);
    }

    public ResponseEntity<Movie> createMovie(Movie movie){

        movie.getRatings().forEach(r-> {
        r.setMovie_ref(movie.getId());
        ratingRepository.save(r);
        });

        Director director = directorRepository.findByName(movie.getDirector().getName()).isEmpty()?
                directorRepository.save(movie.getDirector()):
               directorRepository.findByName(movie.getDirector().getName()).get(0);
        director.appendMovieToMoviesDirected(movie);
        movie.setDirector(director);
        return new ResponseEntity( movieRepository.save(movie), HttpStatus.MULTI_STATUS);
    }

    public ResponseEntity<Movie> updateMovie(Long id, Movie movie){
        movie.setName(movie.getName());
        movie.setRatings(movie.getRatings());
        movie.setDirector(movie.getDirector());
        return new ResponseEntity(movieRepository.save(movie), HttpStatus.MULTI_STATUS);
    }

    public ResponseEntity<Movie> addRatingToMovie(Long movieId, Rating rating){
        Movie movie = movieRepository.findById(movieId).get();
        List<Rating> ratingsUpdatedList = movie.getRatings();
        ratingsUpdatedList.add(rating);
        movie.setRatings(ratingsUpdatedList);
        return  new ResponseEntity(movieRepository.save(movie), HttpStatus.MULTI_STATUS);
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */

    public ResponseEntity<List<Movie>> findByDirector(String directorName){
        return new ResponseEntity<>(movieRepository.findByDirectorName(directorName), HttpStatus.MULTI_STATUS);
    }

    public ResponseEntity<List<Movie>> findMoviesWithRatingHigerThan(Long rating){
        System.out.println("ALL DIRECTORS: "+directorRepository.findByName("Pixar"));
        List<Movie> movies = movieRepository.findAll();
        List<Movie> diff = new ArrayList<>();
        movies.forEach(mov ->{
           mov.getRatings().forEach(rat->{
               if (rat.getRatingValue() <= rating) {
                   diff.add(mov);
               }
           });
        });
        movies.removeAll(diff);
        return new ResponseEntity(movies, HttpStatus.MULTI_STATUS);
    }
}
