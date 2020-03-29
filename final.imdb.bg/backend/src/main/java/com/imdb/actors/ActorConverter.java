package com.imdb.actors;

import com.imdb.actors.entities.Actor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
@Component
public class ActorConverter {

    public Actor convertToEntity(final String name) {
        if (isNull(name)) {
            return null;
        }
        Actor actor = new Actor();
        actor.setFullName(name);
        return actor;
    }
}
