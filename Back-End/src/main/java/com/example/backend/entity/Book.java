package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "BookID")
public class Book {
    @Id
    private String bookId;

    private String bookname;
    private String author;
    private String type;
    private Double price;
    private String description;
    private String inventory;
    private String image;

    public Book(String bookId, String bookName, String author, String type, Double price, String description, String inventory, String image) {
        this.bookId = bookId;
        this.bookname = bookName;
        this.author = author;
        this.type = type;
        this.price = price;
        this.description = description;
        this.inventory = inventory;
        this.image = image;
    }

    public Book() {

    }
}
