package com.example.datnguyen.movie.Repository;

import com.example.datnguyen.movie.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,String>, JpaSpecificationExecutor<Movie> {

    Page<Movie> findByIsActiveTrue(Pageable pageable);

    @Override
    Optional<Movie> findById(String s);
}
