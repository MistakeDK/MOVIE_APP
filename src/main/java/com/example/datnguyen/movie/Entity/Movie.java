package com.example.datnguyen.movie.Entity;


import com.example.datnguyen.movie.Enum.CategoryMovie;
import com.example.datnguyen.movie.Enum.TypeMovie;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String slugName;
    String name;
    @Enumerated(EnumType.STRING)
    CategoryMovie categoryMovie;
    @Enumerated(EnumType.STRING)
    TypeMovie typeMovie;
    String img;
    Double price;
    Boolean isActive;
    @OneToMany(orphanRemoval = true,mappedBy = "movie",cascade = CascadeType.ALL)
    Set<Episode> episodes;
}
