package movies.entities;

import actors.Actor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

import java.util.Set;

import static constants.Constants.UUID_SIZE;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = UUID_SIZE)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String picture_path;

    @Column(nullable = false)
    private String trailer_url;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "actors_participant_in_movies",
            joinColumns = {@JoinColumn(name = "movie_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_movies"))},
            inverseJoinColumns = {@JoinColumn(name = "actor_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_actors_participant_in_movies_actors"))})
    private Set<Actor> actors;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_rating",
            joinColumns = {@JoinColumn(name = "user_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_movie_rating_users"))},
            inverseJoinColumns = {@JoinColumn(name = "movie_id", nullable = false,
                    foreignKey = @ForeignKey(name = "fk_movie_rating_movies"))})
    private String rating;


}
