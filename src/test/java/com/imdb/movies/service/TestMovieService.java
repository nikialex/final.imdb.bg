package com.imdb.movies.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;


import com.imdb.movies.entities.Genre;
import com.imdb.movies.models.MovieModel;

import com.imdb.users.model.UserModel;
import com.imdb.users.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestMovieService {

    @Autowired
    private com.imdb.movies.services.MovieService MovieService;

    @Autowired
    private UserService userService;

    @Test
    public void testCRUDMovie() {
        final MovieModel movie = buildMovie();

        final MovieModel created = MovieService.createMovie(movie);

        assertEquals(movie.getActors(), created.getActors());
        assertEquals(movie.getGenre(), created.getGenre());
        assertEquals(movie.getName(), created.getName());
        assertEquals(movie.getName() + created.getId()  + ".jpg", created.getName() + created.getId() + ".jpg");
        assertEquals(movie.getTrailer_url(), created.getTrailer_url());

        final MovieModel byId = MovieService.getById(created.getId());
        assertNotNull(byId);

        byId.setName("Changed");
        final MovieModel updated = MovieService.updateMovie(byId);
        assertEquals(byId.getName(), updated.getName());

        final List<MovieModel> allMovies = MovieService.getAllMovies();
        assertTrue(allMovies.size() > 0);

        MovieService.deleteMovie(updated.getId());

        final MovieModel deleted = MovieService.getById(updated.getId());
        assertNull(deleted);
    }

    private MovieModel buildMovie() {
        final UserModel user = new UserModel(null, "kiro", "1234", "Kiril", "Petkov");
        final UserModel createdUser = userService.registerUser(user);

        final MovieModel movieModel = new MovieModel();
        File pic = new File("C:\\Users\\Pragmatic10\\Desktop\\niki\\backend\\final.imdb.bg\\src\\test\\resources\\subscri.PNG");
        movieModel.setGenre(Genre.Comedy);
        movieModel.setName("Test");
        movieModel.setPicture_path(pic);
        // movieModel.setRating(movie.getRating());
        movieModel.setTrailer_url("url");
        movieModel.setYear(1997);
        movieModel.setActors("ivan,NIki");
        return movieModel;
    }

}
