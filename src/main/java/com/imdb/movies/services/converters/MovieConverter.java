package com.imdb.movies.services.converters;


import com.imdb.actors.entities.Actor;
import com.imdb.actors.services.ActorService;
import com.imdb.exceptions.HttpBadRequestException;
import com.imdb.movies.entities.Movie;
import com.imdb.movies.models.MovieModel;
import com.mysql.cj.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


@Component
@Log4j2
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
            movieModel.setPicture_path(convertArrayOfBytesToPictureFile(movie));
            // movieModel.setRating(movie.getRating());
            movieModel.setTrailer_url(movie.getTrailerUrl());
            movieModel.setYear(movie.getYear());
            movieModel.setActors(toActors(movie.getActors()));

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
            movie.setImage(convertPictureToArrayOfBytes(model.getPicture_path()));
            //  movie.setRating(model.getRating());
            movie.setTrailerUrl(model.getTrailer_url());
            movie.setYear(model.getYear());

            return movie;


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

    private byte[] convertPictureToArrayOfBytes(final File moviePic) {
        if (isNull(moviePic)) {
            throw new HttpBadRequestException("No picture is found");
        }

        try {
            byte[] byteObjects =null;
            Path path = Paths.get(moviePic.getAbsolutePath());
            byteObjects = Files.readAllBytes(path);
            return byteObjects;
        } catch (IOException e) {
            throw new HttpBadRequestException("No picture is found");
        }
    }

    private File convertArrayOfBytesToPictureFile(Movie movie) {
        if (isNull(movie.getImage())) {
            throw new HttpBadRequestException("No picture is found into the database");
        }
        String directory = "/resources";
        String filename = movie.getName() + movie.getId() + ".jpg";
        File file = new File(directory, filename);
        try {
            FileUtils.writeByteArrayToFile(file, movie.getImage());
        } catch (IOException e) {
            throw new HttpBadRequestException("No picture is found");
        }
        return file;
    }

}
