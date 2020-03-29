package com.imdb.actors.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Optional<Actor> findByFullName(String fullName);

    Optional<Actor> findById(long id);
}
