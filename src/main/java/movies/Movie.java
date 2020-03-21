package movies;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;

import static constants.Constants.UUID_SIZE;

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
}
