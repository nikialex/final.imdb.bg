package com.imdb.actors.services;

import com.imdb.actors.entities.Actor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


public interface ActorService {

    Actor create(String fullName);

    Actor getByName(String fullName);

    Actor getById(long id);
}
