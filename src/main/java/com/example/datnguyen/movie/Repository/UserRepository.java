package com.example.datnguyen.movie.Repository;

import com.example.datnguyen.movie.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    boolean existsByGmail(String gmail);
}
