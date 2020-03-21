package com.imdb.movies.rest;


import com.imdb.movies.models.MovieModel;
import com.imdb.movies.services.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService MovieService;
    private final MovieAccessValidator MovieAccessValidator;

    public MovieController(final com.imdb.movies.services.MovieService MovieService,
                           final MovieAccessValidator MovieAccessValidator) {
        this.MovieService = MovieService;
        this.MovieAccessValidator = MovieAccessValidator;
    }

    @PostMapping
    public MovieModel createMovie(@RequestBody final MovieModel Movie) {
        return MovieService.createMovie(Movie);
    }

    @GetMapping("/all")
    public List<MovieModel> getAllMovies() {
        return MovieService.getAllMovies();
    }

    @PutMapping
    public MovieModel updateMovie(@RequestBody final MovieModel Movie) {
        // MovieAccessValidator.validateUserCanEditMovie(Movie.getUser().getId(), Movie.getId());

        return MovieService.updateMovie(Movie);
    }

    @DeleteMapping("/{id}/{userId}")
    public void deleteMovie(@PathVariable final Long id, @PathVariable final String userId) {
        //    MovieAccessValidator.validateUserCanEditMovie(userId, id);

        MovieService.deleteMovie(id);
    }
}
