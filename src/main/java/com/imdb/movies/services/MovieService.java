package com.imdb.movies.services;

import com.imdb.movies.models.MovieModel;

import java.util.List;

public interface MovieService {
    MovieModel createMovie(MovieModel model);

    void deleteMovie(long id);

    MovieModel updateMovie(MovieModel model);

    MovieModel getById(long id);

    List<MovieModel> getAllMovies();
}
