package com.imdb.movies.rest;


import com.imdb.movies.models.MovieModel;
import com.imdb.movies.services.MovieService;
import com.imdb.users.model.UserModel;
import com.imdb.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieAccessValidator movieAccessValidator;
    private final UserService userService;

    public MovieController(final com.imdb.movies.services.MovieService MovieService,
                           final MovieAccessValidator MovieAccessValidator,
                           final UserService userService) {
        this.movieService = MovieService;
        this.movieAccessValidator = MovieAccessValidator;
        this.userService = userService;
    }

    @PostMapping
    public MovieModel createMovie(@RequestBody final MovieModel Movie) {
        return movieService.createMovie(Movie);
    }

    @GetMapping("/all")
    public List<MovieModel> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PutMapping
    public MovieModel updateMovie(@RequestBody final MovieModel Movie) {
        return movieService.updateMovie(Movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable final Long id) {
        movieService.deleteMovie(id);
    }
}
