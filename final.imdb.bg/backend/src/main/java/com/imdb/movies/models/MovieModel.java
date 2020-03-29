package com.imdb.movies.models;

import com.imdb.movies.entities.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {
    private long id;
    private String name;
    private int year;
    private Genre genre;
    private String picture_path;
    private byte[] image;
    private String trailer_url;
    private String actors;
    private String rating;
}
