package com.vedant.LifeOps.repo;

import com.vedant.LifeOps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
}
