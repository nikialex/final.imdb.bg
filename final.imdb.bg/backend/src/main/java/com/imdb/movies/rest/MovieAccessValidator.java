package com.imdb.movies.rest;

import com.imdb.exceptions.HttpForbiddenException;
import com.imdb.movies.models.MovieModel;
import com.imdb.movies.services.MovieService;
import com.imdb.users.entities.User;
import com.imdb.users.service.UserService;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class MovieAccessValidator {

    private final MovieService movieService;
    private final UserService userService;

    public MovieAccessValidator(final MovieService movieService, final UserService userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    public void validateUserMovieEdit(final String userId, final long movieId) {
        if (isNull(userId) || isNull(movieId)) {
            throw new HttpForbiddenException();
        }

        final MovieModel movie = movieService.getById(movieId);
        final User user = userService.getUserById(userId);

        if (user == null) {
            final String message = String
                    .format("Movie with name " + movie.getName() + " cannot be edited if you are not registered");
            throw new HttpForbiddenException(message);
        }
    }
}
