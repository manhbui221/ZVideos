package com.group2.ZVideos.controller;

import com.group2.ZVideos.model.Movie;
import com.group2.ZVideos.service.FileUploadService;
import com.group2.ZVideos.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
@RequestMapping("/admin/movies")
public class AdminMovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private FileUploadService fileUploadService;

    // Danh sách phim
    @GetMapping
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "admin/movie-list";
    }

    // Hiển thị form thêm/sửa phim
    @GetMapping("/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "admin/movie-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditMovieForm(@PathVariable Long id, Model model) {
        Optional<Movie> movie = movieService.getMovieById(id);
        movie.ifPresent(value -> model.addAttribute("movie", value));
        return movie.isPresent() ? "admin/movie-form" : "redirect:/admin/movies";
    }

    // Lưu phim (Thêm hoặc Cập nhật)
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            @RequestParam("videoFile") MultipartFile videoFile) {

        if (!imageFile.isEmpty()) {
            String imagePath = fileUploadService.saveFile(imageFile, false);
            if (imagePath != null) {
                movie.setImagePath(imagePath);
            }
        }

        if (!videoFile.isEmpty()) {
            String videoPath = fileUploadService.saveFile(videoFile, true);
            if (videoPath != null) {
                movie.setVideoPath(videoPath);
            }
        }

        movieService.saveMovie(movie);
        return "redirect:/admin/movies";
    }

}
