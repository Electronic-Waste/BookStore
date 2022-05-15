package com.example.backend.entity;

import lombok.Data;

@Data
public class Book {
    private String BookID;

    private String BookName;
    private String Author;
    private String Type;
    private double Price;
    private String Description;
    private String Inventory;
    private String Image;

    public Book(String bookID, String bookName, String author, String type, double price, String description, String inventory, String image) {
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
