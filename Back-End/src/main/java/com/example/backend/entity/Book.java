package com.example.backend.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Book {
    private String BookID;

    private String BookName;
    private String Author;
    private String Type;
    private Double Price;
    private String Description;
    private String Inventory;
    private String Image;

    public Book(String bookID, String bookName, String author, String type, Double price, String description, String inventory, String image) {
        this.BookID = bookID;
        this.BookName = bookName;
        this.Author = author;
        this.Type = type;
        this.Price = price;
        this.Description = description;
        this.Inventory = inventory;
        this.Image = image;
    }

}
