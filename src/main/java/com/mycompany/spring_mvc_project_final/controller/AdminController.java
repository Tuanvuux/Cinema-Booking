package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.Category;
import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.repository.CategoryRepository;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/admin/addMovie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        setCategoryDropDownList(model);
        return "admin/addMovie";
    }

    @PostMapping("/admin/saveMovie")
    public String saveMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCategoryDropDownList(model);
            return "admin/addMovie";
        }
        movieRepository.save(movie);
        return "redirect:/home";
    }
    @GetMapping("/admin/editMovie/{movieId}")
    public String showEditMovieForm(@PathVariable Long movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).orElse(null);
        if (movie == null) {
            return "error";
        }
        model.addAttribute("movie", movie);
        return "admin/editMovie";
    }

    @PostMapping("/admin/updateMovie")
    public String updateMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "admin/editMovie";
        }
        movieRepository.save(movie);
        return "redirect:/";
    }

    @GetMapping("/admin/deleteMovie/{movieId}")
    public String deleteMovie(@PathVariable Long movieId) {
        movieRepository.deleteById(movieId);
        return "redirect:/";
    }


    private void setCategoryDropDownList(Model model){
        List<Category> cateList = (List<Category>) categoryRepository.findAll();
        if(!cateList.isEmpty()){
            Map<Long,String> cateMap = new LinkedHashMap<>();
            for(Category category : cateList){
                cateMap.put(category.getCategoryId(), category.getCateName());
            }
            model.addAttribute("categoryList", cateMap);
        }
    }
}
