package com.aitinews.articles.service.converters;

import com.aitinews.articles.entities.Article;
import com.aitinews.articles.models.ArticleModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toList;

@Component
@Log4j2
public class ArticleConverter {


    public ArticleModel convertToModel(Article article) {

        if (!isNull(article)) {
            final ArticleModel articleModel = new ArticleModel();
            articleModel.setId(article.getId());
            articleModel.setText(article.getText());
            articleModel.setTitle(article.getTitle());
            return articleModel;
        }
        return null;
    }


    public Article convertToEntity(ArticleModel articleModel) {

        if (!isNull(articleModel)) {
            final Article article = new Article();
            article.setId(articleModel.getId());
            article.setText(articleModel.getText());
            article.setTitle(articleModel.getTitle());
            return article;
        }
        return null;
    }


    public List<ArticleModel> convertToModels(final List<Article> Articles) {
        if (isNull(Articles) || Articles.isEmpty()) {
            return new ArrayList<>();
        }
        return Articles.stream().map(this::convertToModel).collect(toList());
    }
}
