package com.bk.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebMvc
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/")
public class Controller implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("create")
    public ResponseEntity createMovie(@RequestBody Movie movieDetails) {
        Movie returnValue = new Movie();
        returnValue.setTitle(movieDetails.getTitle());
        returnValue.setDirector(movieDetails.getDirector());
        returnValue.setDistributor(movieDetails.getDistributor());
        returnValue.setRating(movieDetails.getRating());
        returnValue.setNumberOfVotes(movieDetails.getNumberOfVotes());
        movieRepository.save(returnValue);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("list")
    public @ResponseBody List<Movie> getAllTheMovies() {
        return (List<Movie>) movieRepository.findAll();
    }

    @GetMapping("list/{id}")
    public ResponseEntity getAllMoviesById(@PathVariable("id") int id) {
        Optional<Movie> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            return new ResponseEntity<>(movieData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable("id") int id) {
        try {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") int id, @RequestBody Movie movieDetails) {
        Optional<Movie> movieData = movieRepository.findById(id);
        if (movieData.isPresent()) {
            Movie _movieData = movieData.get();
            _movieData.setTitle(movieDetails.getTitle());
            _movieData.setDirector(movieDetails.getDirector());
            _movieData.setDistributor(movieDetails.getDistributor());
            _movieData.setRating(movieDetails.getRating());
            _movieData.setNumberOfVotes(movieDetails.getNumberOfVotes());
            return new ResponseEntity<>(movieRepository.save(_movieData), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
