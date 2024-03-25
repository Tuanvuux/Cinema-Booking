package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping
public class HomePageController {
    @Autowired
    MovieRepository movieRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showMovies(Model model, HttpSession session) {
        String lastName = (String) session.getAttribute("lastName");
        List<Movie> movieList = (List<Movie>) movieRepository.findAll();
        model.addAttribute("lastName", lastName);
        model.addAttribute("movieList", movieList);
        return "home";
    }
}