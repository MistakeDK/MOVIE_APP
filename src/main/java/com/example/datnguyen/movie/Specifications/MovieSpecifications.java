package com.example.datnguyen.movie.Specifications;

import com.example.datnguyen.movie.Entity.Movie;
import com.example.datnguyen.movie.Enum.CategoryMovie;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class MovieSpecifications {
    public static Specification<Movie> hasName(String name){
        if(Objects.isNull(name)){
            return (((root, query, criteriaBuilder) ->
                    criteriaBuilder.conjunction()));
        }
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"),"%"+name+"%"));
    }
    public static Specification<Movie> hasCategory(String category){
        if(CategoryMovie.valueOf(category)==CategoryMovie.ALL){
            return ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
        }
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoryMovie"), CategoryMovie.valueOf(category)));
    }
    public static Specification<Movie> hasIsActive(String isActive){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isActive"),Boolean.valueOf(isActive)));
    }
}
