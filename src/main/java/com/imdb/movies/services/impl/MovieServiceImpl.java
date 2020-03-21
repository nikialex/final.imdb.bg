package com.imdb.movies.services.impl;

import com.imdb.exceptions.HttpBadRequestException;
import com.imdb.movies.entities.Movie;
import com.imdb.movies.entities.MovieRepository;
import com.imdb.movies.models.MovieModel;
import com.imdb.movies.services.MovieService;
import com.imdb.movies.services.converters.MovieConverter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;


    public MovieServiceImpl(final MovieRepository movieRepository,
                            final MovieConverter movieConverter) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
    }

    @Override
    @Transactional
    public MovieModel createMovie(MovieModel model) {

        log.info("Create movie BEGIN: {}", model);

        final Movie entity = movieConverter.convertToEntity(model);

        final Movie movie = movieRepository.save(entity);

        final MovieModel created = movieConverter.convertToModel(movie);

        log.info("Create movie END: {}", created);

        return created;

    }

    @Override
    public void deleteMovie(long id) {

        log.info("Delete movie by id BEGIN: {}", id);

        movieRepository.deleteById(id);

        log.info("Delete movie by id END: {}", id);

    }

    @Transactional
    @Override
    public MovieModel updateMovie(MovieModel model) {
        log.info("Update movie BEGIN: {}", model);

        if (!movieRepository.existsById(model.getId())) {
            throw new HttpBadRequestException("Movie entity does not exist for id: " + model.getId());
        }

        final Movie car = movieConverter.convertToEntity(model);

        final MovieModel updated = movieConverter.convertToModel(movieRepository.save(car));

        log.info("Update car END: {}", updated);

        return updated;
    }

    @Override
    public MovieModel getById(long id) {
        log.info("Get movie by id BEGIN: {}", id);

        final Optional<Movie> movieOpt = movieRepository.findById(id);

        MovieModel movie = null;
        if (movieOpt.isPresent()) {
            movie = movieConverter.convertToModel(movieOpt.get());
        }

        log.info("Get movie by id END: {} {}", id, movie);

        return movie;
    }

    @Override
    public List<MovieModel> getAllMovies() {

        log.info("Get all com.imdb.actors.movies BEGIN: ");

        final List<Movie> all = movieRepository.findAll();

        final List<MovieModel> movies = movieConverter.convertToModels(all);

        log.info("Get all com.imdb.actors.movies END: {}", movies);

        return movies;
    }
}
