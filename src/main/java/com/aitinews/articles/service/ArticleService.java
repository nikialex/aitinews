package com.aitinews.articles.service;

import com.aitinews.articles.models.ArticleModel;

import java.util.List;

public interface ArticleService {

    ArticleModel createArticle(ArticleModel model);

    void deleteArticle(long id);

    ArticleModel updateArticle(ArticleModel model);

    ArticleModel getById(long id);

    List<ArticleModel> getAllArticles();
}
