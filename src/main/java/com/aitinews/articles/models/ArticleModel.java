package com.aitinews.articles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleModel {
    private long id;
    private String title;
    private String text;
}
