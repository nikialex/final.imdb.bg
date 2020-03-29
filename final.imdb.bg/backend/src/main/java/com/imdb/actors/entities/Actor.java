package com.imdb.actors.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String fullName;



}
