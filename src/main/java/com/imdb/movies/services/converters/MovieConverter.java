package com.imdb.movies.services.converters;


import com.imdb.actors.entities.Actor;
import com.imdb.actors.services.ActorService;
import com.imdb.movies.entities.Movie;
import com.imdb.movies.models.MovieModel;
import com.mysql.cj.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Component
public class MovieConverter {

    private final ActorService actorService;

    public MovieConverter(final ActorService actorService
    ) {
        this.actorService = actorService;

    }


    public MovieModel convertToModel(final Movie movie) {

        if (!isNull(movie)) {
            final MovieModel movieModel = new MovieModel();
            movieModel.setId(movie.getId());
            movieModel.setGenre(movie.getGenre());
            movieModel.setName(movie.getName());
            movieModel.setPicture_path(movie.getPicturePath());
            // movieModel.setRating(movie.getRating());
            movieModel.setTrailer_url(movie.getTrailerUrl());
            movieModel.setYear(movie.getYear());
            movieModel.setActors(toActors(movie.getActors()));


//            final MovieModel movieModel = MovieModel.builder()
//                    .id(movie.getId())
//              //      .actors(toActors(movie.getActors()))
//                    .genre(movie.getGenre())
//                    .name(movie.getName())
//                    .picturePath(movie.getPicturePath())
//                    .trailerUrl(movie.getTrailerUrl())
//                    .year(movie.getYear())
//                    .rating(movie.getRating())
//                    .build();
            return movieModel;
        }

        return null;
    }

    private String toActors(final Set<Actor> actors) {
        if (isNull(actors) || actors.isEmpty()) {
            return null;
        }

        return String.join(",", actors.stream().map(Actor::getFullName).collect(toSet()));
    }


    public Movie convertToEntity(final MovieModel model) {

        if (!isNull(model)) {


            final Movie movie = new Movie();
            movie.setId(model.getId());
            movie.setActors(createActorsIfMissing(model.getActors()));
            movie.setGenre(model.getGenre());
            movie.setName(model.getName());
            movie.setPicturePath(model.getPicture_path());
            //  movie.setRating(model.getRating());
            movie.setTrailerUrl(model.getTrailer_url());
            movie.setYear(model.getYear());

            return movie;


//            final Movie movie = Movie.builder()
//                    .id(model.getId())
//                    //    .actors(createActorsIfMissing(model.getActors()))
//                    .genre(model.getGenre())
//                    .name(model.getName())
//                    .picturePath(model.getPicturePath())
//                    .rating(model.getRating())
//                    .trailerUrl(model.getTrailerUrl())
//                    .year(model.getYear())
//                    .build();
//            return movie;
        }

        return null;
    }

    private Set<Actor> createActorsIfMissing(final String actors) {
        if (StringUtils.isNullOrEmpty(actors)) {
            return null;
        }

        final Set<String> actorsSet = new HashSet<>(Arrays.asList(actors.split(",")));
        final Set<Actor> entities = new HashSet<>();

        actorsSet.forEach(actor -> {
            final Actor byName = actorService.getByName(actor);
            if (!isNull(byName)) {
                entities.add(byName);
            } else {
                entities.add(actorService.create(actor));
            }
        });

        return entities;
    }

    public List<MovieModel> convertToModels(final List<Movie> movies) {
        if (isNull(movies) || movies.isEmpty()) {
            return new ArrayList<>();
        }
        return movies.stream().map(this::convertToModel).collect(toList());
    }
}
