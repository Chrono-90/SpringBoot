package com.example.springboot.repository;

import com.example.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
//    @Query("select u from User u where u.name=: n") -> @RequestParam("n")
    User findUserByName(String name);

}
