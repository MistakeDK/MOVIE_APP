package com.example.datnguyen.movie.Repository;

import com.example.datnguyen.movie.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByGmail(String gmail);

    Optional<User> findByGmail(String gmail);
}
