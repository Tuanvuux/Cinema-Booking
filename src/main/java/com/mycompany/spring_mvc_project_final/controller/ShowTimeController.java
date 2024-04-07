package com.mycompany.spring_mvc_project_final.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import com.mycompany.spring_mvc_project_final.repository.ShowTimeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class ShowTimeController {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/showtime")
    public String showTime(Model model) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", new Locale("vi", "VN"));
        List<String> dates = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();

        // Add current date and day of week
        dates.add(currentDate.format(dateFormatter));
        daysOfWeek.add(currentDate.format(dayFormatter));

        // Add next 6 days and their corresponding days of week
        for (int i = 1; i < 7; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            dates.add(nextDate.format(dateFormatter));
            daysOfWeek.add(nextDate.format(dayFormatter));
        }

        model.addAttribute("dates", dates);
        model.addAttribute("daysOfWeek", daysOfWeek);

        return "ShowTime";
    }
//    @Transactional
//    @PostMapping("/processSelectedDate")
//    public ResponseEntity<String> processSelectedDate(@RequestBody Map<String, String> payload, Model model) {
//        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MM yyyy");
//        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            String selectedDateStr = payload.get("selectedDate");
//            Date date = inputFormat.parse(selectedDateStr);
//            String outputDate = outputFormat.format(date);
//
//            List<Movie> movies = movieRepository.findMovie(outputDate);
//
//            System.out.println("ok");
//            if (!movies.isEmpty()) {
//                for (Movie movie : movies) {
//                    Hibernate.initialize(movie.getPost());
//                }
//            }
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonMovies = objectMapper.writeValueAsString(movies);
//            return ResponseEntity.ok(jsonMovies);
//        } catch (ParseException | JsonProcessingException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing selected date");
//        }
//    }

}
