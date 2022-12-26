package com.example.backend.repository;

import com.example.backend.entity.Book;
import com.example.backend.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bookimage", path = "bookimage")
public interface BookImageRepository extends MongoRepository<BookImage, String> {
    /* We have implemented book logic with Redis, so we do not need to use this function */
    List<BookImage> findByBookId(@Param("bookid") int bookId);
}
