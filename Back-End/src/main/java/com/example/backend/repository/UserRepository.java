package com.example.backend.repository;

import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userId = :userid and u.password = :password")
    User checkUser(@Param("userid") Integer userid, @Param("password") String password);

    @Query("select u from User u")
    List<User> getUsers();

    @Query("select u from User u where u.username = :username")
    List<User> getRepeatUsers(@Param("username") String username);
}
