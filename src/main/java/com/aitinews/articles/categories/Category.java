package com.aitinews.articles.categories;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String categoryName;
}
