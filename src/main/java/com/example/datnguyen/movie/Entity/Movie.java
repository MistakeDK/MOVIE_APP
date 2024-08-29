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
    @Column(nullable = false)
    String slugName;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    CategoryMovie categoryMovie;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    TypeMovie typeMovie;
    @Column(nullable = false)
    Double price;
    @Column(nullable = false)
    Boolean isActive;
    @Column(nullable = false)
    String posterUrl;
    @Column(nullable = false)
    String thumbUrl;
    @OneToMany(orphanRemoval = true,mappedBy = "movie",cascade = CascadeType.ALL)
    Set<Episode> episodes;
}
