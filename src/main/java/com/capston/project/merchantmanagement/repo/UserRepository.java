package com.capston.project.merchantmanagement.repo;

import com.capston.project.merchantmanagement.entities.Users;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.css.CSSRule;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findByEmail(String email);
}
