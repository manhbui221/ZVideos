package com.group2.ZVideos.controller;

import com.group2.ZVideos.model.Movie;
import com.group2.ZVideos.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    private com.group2.ZVideos.service.MovieService movieService;

    @GetMapping("/")
    public String index(Model model) {
        List<Movie> movies = movieService.getAllMovies();

        if (!movies.isEmpty()) {
            Random random = new Random();
            Movie randomMovie = movies.get(random.nextInt(movies.size()));
            model.addAttribute("featuredMovie", randomMovie);
        }

        model.addAttribute("movies", movieService.getAllMovies());
        return "index";
    }


}
