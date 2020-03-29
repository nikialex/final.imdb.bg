package com.imdb.actors.services;

import com.imdb.actors.ActorConverter;
import com.imdb.actors.entities.Actor;
import com.imdb.actors.entities.ActorRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@Log4j2
public class ActorServiceImpl implements ActorService {

    private final ActorConverter actorConverter;
    private final ActorRepository actorRepository;


    public ActorServiceImpl(final ActorConverter actorConverter,
                            final ActorRepository actorRepository) {
        this.actorConverter = actorConverter;
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor create(String fullName) {
        log.info("Create actor BEGIN: {}", fullName);

        if (isNull(fullName)) {
            return null;
        }
        final Actor actor = actorConverter.convertToEntity(fullName);
        final Actor created = actorRepository.save(actor);

        log.info("Create actor END: {}", created);
        return created;
    }

    @Override
    public Actor getByName(String fullName) {

        log.info("Get extra by name BEGIN: {}", fullName);
        if (isNull(fullName)) {
            return null;
        }

        final Optional<Actor> extraOpt = actorRepository.findByFullName(fullName);
        final Actor actor = extraOpt.orElse(null);
        log.info("Get extra by name BEGIN: {}", actor);

        return actor;

    }

    @Override
    public Actor getById(long id) {

        log.info("Get extra by name BEGIN: {}", id);
        if (isNull(id)) {
            return null;
        }

        final Optional<Actor> extraOpt = actorRepository.findById(id);
        final Actor actor = extraOpt.orElse(null);
        log.info("Get extra by name BEGIN: {}", actor);

        return actor;

    }
}
