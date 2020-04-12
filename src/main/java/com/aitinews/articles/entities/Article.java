package com.aitinews.articles.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

//    @OneToMany(
//            mappedBy = "categories",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    private List<Category> comments = new ArrayList<>();
}
