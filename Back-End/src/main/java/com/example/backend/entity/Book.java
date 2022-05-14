package com.example.backend.entity;

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

    public String getBookID() {
        return BookID;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthor() {
        return Author;
    }

    public String getType() {
        return Type;
    }

    public double getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    public String getInventory() {
        return Inventory;
    }

    public String getImage() {
        return Image;
    }

    public void setBookID(String bookID) {
        BookID = bookID;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setInventory(String inventory) {
        Inventory = inventory;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setType(String type) {
        Type = type;
    }

}
