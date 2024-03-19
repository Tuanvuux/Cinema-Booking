package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/movie")
public class ViewController {
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(method = GET)
    public String showMovies(Model model) {
        List<Movie> movieList = (List<Movie>) movieRepository.findAll();
        model.addAttribute("movieList", movieList);
        return "movie/MovieList";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(
        @RequestParam("searchInput") String searchInput, Model model) {
        List<Movie> movieList;
        if(searchInput.isEmpty()) {
            movieList = (List<Movie>) movieRepository.findAll();
        } else {
            movieList = movieRepository.findByMovieNameOrDirectorOrProducerOrActor(searchInput, searchInput, searchInput, searchInput);
        }
        model.addAttribute("movieList", movieList);
        return "movie/MovieList";
    }

    @GetMapping("/movieDetail")
    public String getMovieDetail(@RequestParam("movieId") Long movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return "error";
        }
        System.out.println(movie.getMovieName()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        model.addAttribute("movie", movie);
        return "MovieDetail";
    }
}
