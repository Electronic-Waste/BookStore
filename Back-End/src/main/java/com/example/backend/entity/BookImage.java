package com.example.backend.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "bookimage")
public class BookImage {

    @Id
    private String id;

    private int bookid;
    private String url;

    public BookImage(String id, int bookid, String url) {
        this.id = id;
        this.bookid = bookid;
        this.url = url;
    }

    public int getBookId() {return bookid;}

    public void setBookId(int userId) {this.bookid = userId;}

    public String getUrl() {return url;}

    public void setUrl(String url) {this.url = url;}
}
