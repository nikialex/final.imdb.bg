package movies.services;

import movies.models.MovieModel;

import java.util.List;

public interface MovieService {
    MovieModel createMovie(MovieModel model);

    void deleteMovie(String id);

    MovieModel updateMovie(MovieModel model);

    MovieModel getById(String id);

    List<MovieModel> getAllMovies();
}
