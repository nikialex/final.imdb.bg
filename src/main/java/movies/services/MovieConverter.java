package movies.services;

import actors.Actor;
import actors.ActorService;
import com.mysql.cj.util.StringUtils;
import movies.entities.Movie;
import movies.models.MovieModel;
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
            final MovieModel movieModel = MovieModel.builder()
                    .id(movie.getId())
                    .actors(toActors(movie.getActors()))
                    .genre(movie.getGenre())
                    .name(movie.getName())
                    .picture_path(movie.getPicture_path())
                    .trailer_url(movie.getTrailer_url())
                    .year(movie.getYear())
                    .rating(movie.getRating())
                    .build();
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
            final Movie movie = Movie.builder()
                    .id(model.getId())
                    .actors(createActorsIfMissing(model.getActors()))
                    .genre(model.getGenre())
                    .name(model.getName())
                    .picture_path(model.getPicture_path())
                    .rating(model.getRating())
                    .trailer_url(model.getTrailer_url())
                    .year(model.getYear())
                    .build();
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
}
