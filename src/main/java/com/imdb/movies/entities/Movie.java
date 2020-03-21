package com.imdb.movies.entities;


import com.imdb.actors.entities.Actor;
import lombok.Data;
import javax.persistence.*;
import java.util.Set;



@Data
@Entity
@Table(name = "movies")
public class Movie {



    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String picturePath;

    @Column(nullable = false)
    private String trailerUrl;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actors_participant_in_movies",
            joinColumns = {@JoinColumn(name = "movie_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_movies"))},
            inverseJoinColumns = {@JoinColumn(name = "actor_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_actors_participant_in_movies_actors"))})
    private Set<Actor> actors;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "movie_rating",
//            joinColumns = {@JoinColumn(name = "user_id", nullable = false,
//                    foreignKey = @ForeignKey(name = "fk_movie_rating_users"))},
//            inverseJoinColumns = {@JoinColumn(name = "movie_id", nullable = false,
//                    foreignKey = @ForeignKey(name = "fk_movie_rating_movies"))})
//    @Column(nullable = false)
//    private String rating;


}
