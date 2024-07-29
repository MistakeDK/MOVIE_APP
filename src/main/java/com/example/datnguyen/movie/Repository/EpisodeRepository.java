package com.example.datnguyen.movie.Repository;

import com.example.datnguyen.movie.Entity.Episode;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode,String> {

    List<Episode> findByMovie_IdOrderByNameAsc(String id);
}
