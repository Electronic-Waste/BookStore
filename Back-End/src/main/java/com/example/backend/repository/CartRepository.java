package com.example.backend.repository;

import com.example.backend.entity.Cart;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.userId = :userid")
    List<Cart> getCartByUserId(@Param("userid") Integer userid);

    @Query("select c from Cart c where c.userId = :userId and c.book.bookId = :bookId")
    Cart getCartByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") int bookId);
}
