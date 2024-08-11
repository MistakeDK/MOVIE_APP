package com.example.datnguyen.movie.Entity;

import com.example.datnguyen.movie.Enum.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(unique = true,nullable = false)
    String gmail;
    @Column(nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    Role role;

}
