package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cart")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class Cart implements Serializable {
    @Id
    private int cartId;
    private int userId;
    private Double price;
    private int num;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

}
