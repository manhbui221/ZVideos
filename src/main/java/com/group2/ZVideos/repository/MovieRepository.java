package com.group2.ZVideos.repository;

import com.group2.ZVideos.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
