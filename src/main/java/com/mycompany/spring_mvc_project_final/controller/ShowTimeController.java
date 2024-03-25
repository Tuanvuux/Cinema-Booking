package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.Movie;
import com.mycompany.spring_mvc_project_final.entities.ShowTime;
import com.mycompany.spring_mvc_project_final.repository.MovieRepository;
import com.mycompany.spring_mvc_project_final.repository.ShowTimeRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Transactional
    @PostMapping("/processSelectedDate")
    @ResponseBody
    public String processSelectedDate(@RequestBody Map<String, String> payload, Model model) {
        String selectedDateStr = payload.get("selectedDate");
        // Xử lý ngày được chọn ở đây
//        System.out.println("Ngày được chọn: " + selectedDateStr);
//
//        // Chuyển đổi từ String sang Date
//        SimpleDateFormat sdf = new SimpleDateFormat("dd MM yyyy");
//        Date selectedDate = null;
//        try {
//            selectedDate = sdf.parse(selectedDateStr);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MM yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parse the input date string
            Date date = inputFormat.parse(selectedDateStr);

            // Format the parsed date to the desired output format
            String outputDate = outputFormat.format(date);

            // Print the output
            System.out.println("Ngày được chọn (kiểu Date): " + outputDate.toString());

            //ist<ShowTime> showTimes = showTimeRepository.findByShowDate(selectedDate);
            List<Movie> movies= movieRepository.findMovie(outputDate.toString());
            if(!movies.isEmpty()){
                for(Movie movie: movies){
                    Hibernate.initialize(movie.getPost());
                }
            }

            //System.out.println(showTimes.size());
            model.addAttribute("movies", movies);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "ShowTime";
    }

}
