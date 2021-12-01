package com.robotech.magellan.moviemanager;

import com.robotech.magellan.moviemanager.controllers.HollyWooController;
import com.robotech.magellan.moviemanager.models.Director;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.models.Rating;
import com.robotech.magellan.moviemanager.repositories.DirectorRepository;
import com.robotech.magellan.moviemanager.repositories.MovieRepository;
import com.robotech.magellan.moviemanager.repositories.RatingRepository;
import com.robotech.magellan.moviemanager.services.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class MoviemanagerApplicationTests {
	@Autowired
	MovieService movieService;

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private DirectorRepository directorRepository;
	@Autowired
	private RatingRepository ratingRepository;
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void getMovies(){
		assertNotNull(movieService.getMovies());
	}

	@Test
	public void findMovie(){
		assertNotNull(movieService.findMovie(2l));
	}


	@Test
	public void updateMovie(){
		assertNotNull(movieService.updateMovie(2l, new Movie()));
	}

	@Test
	void addRatingToMovie(){
		// given
		ResponseEntity<Movie> response = movieService.addRatingToMovie(2l, new Rating(3l));

		//then
		assertNotNull(response);
		assertEquals(HttpStatus.MULTI_STATUS, response.getStatusCode());
	}


    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */
	@Test
	void findByDirector(){
		//GIVEN
		String name = "Incredibles 2";
		List<Rating> ratings = new ArrayList<Rating>(){{
			add(new Rating(5L));
			add(new Rating(6L));
		}};
		Director director = new Director("Pixar", 45L);
		Movie movie = movieRepository.save(new Movie(name, ratings, directorRepository.save(director)));
		//WHEN
		ResponseEntity<List<Movie>> response = movieService.findByDirector("Pixar");

		//THEN
		assertTrue( response.getBody().size()>0);
		assertNotNull(response);
	}

	@Test
	void findMoviesWithRatingHigerThan(){

		assertNotNull(
				movieService.findMoviesWithRatingHigerThan(5L)
						.getBody());
		assertTrue(
				movieService.findMoviesWithRatingHigerThan(5L)
						.getBody()
						.size() > 0);
		movieService.findMoviesWithRatingHigerThan(5L)
				.getBody().forEach( mov -> {
					mov.getRatings().forEach( rat ->{
						assertTrue(rat.getRatingValue() > 5);
					});
				});
	}

}
