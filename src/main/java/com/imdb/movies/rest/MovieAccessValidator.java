package com.imdb.movies.rest;

import com.imdb.movies.services.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieAccessValidator {

    private final MovieService movieSercive;

    public MovieAccessValidator(final MovieService movieSercive) {
        this.movieSercive = movieSercive;
    }

//    void validateUserCanEditCar(final String userId, final String movieId) {
//        if (isNull(userId) || isNull(movieId)) {
//            throw new HttpForbiddenException();
//        }
//
//        final MovieModel movie = movieServive.getById(movieId);
//
//        if (!userId.equals(movie.getId())) {
//            final String message = String
//                    .format("Car with id: %s does not belong to user with id: %s", carId, userId);
//            throw new HttpForbiddenException(message);
//        }
//    }
}
