package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.Category;
import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.repository.CategoryRepository;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.ws.rs.GET;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping("/addMovie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        setCategoryDropDownList(model);
        return "admin/addMovie";
    }

    @PostMapping("/saveMovie")
    public String saveMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            setCategoryDropDownList(model);
            return "admin/addMovie";
        }
        movieRepository.save(movie);
        return "redirect:/home";
    }

    @RequestMapping(value = "/editMovie", method = RequestMethod.GET)
    public String showEditMovie(@RequestParam("id") Long movieId, Model model) {
        Movie movie = movieRepository.findByMovieId(movieId);
        model.addAttribute("movie", movie);
        setCategoryDropDownList(model);
        return "admin/editMovie";
    }

    @RequestMapping(value = "/updateMovie", method = RequestMethod.POST)
    public String updateMovie(@ModelAttribute Movie movie){
        System.out.println("3333");
        movieRepository.save(movie);
        return "redirect:/";
    }





//    @GetMapping("/admin/deleteMovie/{movieId}")
//    public String deleteMovie(@PathVariable Long movieId) {
//        movieRepository.deleteById(movieId);
//        return "redirect:/";
//    }


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
