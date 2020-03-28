package com.imdb.movies.rest;


import static org.junit.jupiter.api.Assertions.assertThrows;
import com.imdb.exceptions.HttpForbiddenException;
import com.imdb.movies.entities.Genre;
import com.imdb.movies.models.MovieModel;
import com.imdb.users.model.UserModel;
import com.imdb.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestMovieAccessValidator {

    @Autowired
    private UserService userService;

    @Autowired
    private com.imdb.movies.rest.MovieAccessValidator MovieAccessValidator;

    @Autowired
    private com.imdb.movies.services.MovieService MovieService;

    @Test
    public void testMovieAccessValidator() {
        final MovieModel Movie = buildMovie();
        final UserModel user = registerUser();
        final MovieModel created = MovieService.createMovie(Movie);

        MovieAccessValidator.validateUserMovieEdit(user.getId(), created.getId());

        assertThrows(
                HttpForbiddenException.class,
                () -> MovieAccessValidator.validateUserMovieEdit("bad id", created.getId()));
    }

    private MovieModel buildMovie() {

        final MovieModel movieModel = new MovieModel();
        movieModel.setGenre(Genre.Comedy);
        movieModel.setName("Test");
        movieModel.setPicture_path("Path");
        // movieModel.setRating(movie.getRating());
        movieModel.setTrailer_url("url");
        movieModel.setYear(1997);
        movieModel.setActors("NIki,ivan");

        return movieModel;

    }


    private UserModel registerUser() {
        final UserModel user = new UserModel(null, "Kircata", "1234", "Kiril", "Petkov");
        final UserModel createdUser = userService.registerUser(user);
        return createdUser;
    }

}
