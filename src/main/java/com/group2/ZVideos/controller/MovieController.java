package com.group2.ZVideos.controller;

import com.group2.ZVideos.model.Movie;
import com.group2.ZVideos.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {

    @Autowired
    private com.group2.ZVideos.service.MovieService movieService;

    @GetMapping("/movie/{id}")
    public String movieDetail(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            List<Movie> movies = movieService.getAllMovies(); // Lấy danh sách phim từ database
            model.addAttribute("movies", movies); // Đưa danh sách phim vào model
            return "movie-detail";
        }

        return "redirect:/";
    }

    @GetMapping("/watch/{id}")
    public String watchMovie(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            return "watch-movie";
        }
        return "redirect:/";
    }

}
