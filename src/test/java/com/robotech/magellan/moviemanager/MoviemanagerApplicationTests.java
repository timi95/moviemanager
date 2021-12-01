package com.robotech.magellan.moviemanager;

import com.robotech.magellan.moviemanager.models.Director;
import com.robotech.magellan.moviemanager.models.Movie;
import com.robotech.magellan.moviemanager.models.Rating;
import com.robotech.magellan.moviemanager.repositories.DirectorRepository;
import com.robotech.magellan.moviemanager.repositories.MovieRepository;
import com.robotech.magellan.moviemanager.repositories.RatingRepository;
import com.robotech.magellan.moviemanager.services.MovieService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
//@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class MoviemanagerApplicationTests {
	@InjectMocks
	MovieService movieService = new MovieService();

	@Mock
	private MovieRepository movieRepository;
	@Mock
	private DirectorRepository directorRepository;
	@Mock
	private RatingRepository ratingRepository;

	private Movie movie;

	@BeforeEach
	 void setup() {
		//GIVEN
		String name = "Incredibles 2";
		List<Rating> ratings = new ArrayList<Rating>(){{
			add(new Rating(5L));
			add(new Rating(6L));
		}};
		Director director = new Director("Pixar", 45L);
		movie = new Movie(name, ratings, director);
//		when().thenThrow();
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
	}


	@Test
	public void updateMovie(){
		assertNotNull(movieService.updateMovie(2l, new Movie()));
	}

	@Test
	void addRatingToMovie(){
		Movie movie = new Movie("Test Movie",
				new ArrayList<Rating>(){{
					add(new Rating(5L));
				}},
				new Director("Testarantino", 45l));
		System.out.println(movieService.getMovies());
		when(movieRepository.findById(1l)).thenReturn(java.util.Optional.of(movie));
		when(movieService.addRatingToMovie(1l, new Rating(3l)))
				.thenReturn(new ResponseEntity(movie, HttpStatus.MULTI_STATUS) );
	}


    /*
    * Search movies by Director
    • Search movies where the rating is above a provided rating. */
	@Test
	void findByDirector(){
		List<Movie> list = new ArrayList<Movie>(){{
			add(movie);
		}};
		//WHEN
		when(movieRepository.findByDirectorName("Pixar")).thenReturn(list);
		assertTrue( movieService.findByDirector("Pixar").getBody().size()>0);
	}

	@Test
	void findMoviesWithRatingHigerThan(){
		List<Movie> list = new ArrayList<Movie>(){{
			add(movie);
		}};
		when(movieRepository.findAll()).thenReturn(list);
		assertNotNull(
				movieService.findMoviesWithRatingHigerThan(5L)
						.getBody());
	}

}
