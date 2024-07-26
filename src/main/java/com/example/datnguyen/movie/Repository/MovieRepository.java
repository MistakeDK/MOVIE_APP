package com.example.datnguyen.movie.Repository;

import com.example.datnguyen.movie.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,String> {
}
