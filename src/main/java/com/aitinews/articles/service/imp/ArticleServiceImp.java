package com.aitinews.articles.service.imp;

import com.aitinews.articles.entities.Article;
import com.aitinews.articles.entities.ArticleRepository;
import com.aitinews.articles.models.ArticleModel;
import com.aitinews.articles.service.ArticleService;
import com.aitinews.articles.service.converters.ArticleConverter;
import com.aitinews.exceptions.HttpBadRequestException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ArticleServiceImp implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleConverter articleConverter;


    public ArticleServiceImp(final ArticleRepository articleRepository,
                             final ArticleConverter articleConverter) {
        this.articleRepository = articleRepository;
        this.articleConverter = articleConverter;
    }


    @Override
    @Transactional
    public ArticleModel createArticle(ArticleModel model) {

        log.info("Create Article BEGIN: {}", model);

        final Article entity = articleConverter.convertToEntity(model);
        final Article movie = articleRepository.save(entity);
        final ArticleModel created = articleConverter.convertToModel(movie);

        log.info("Create Article END: {}", created);

        return created;

    }

    @Override
    public void deleteArticle(long id) {
        log.info("Delete Article by id BEGIN: {}", id);

        articleRepository.deleteById(id);

        log.info("Delete Article by id END: {}", id);
    }

    @Override
    public ArticleModel updateArticle(ArticleModel model) {
        log.info("Update Article BEGIN: {}", model);

        if (!articleRepository.existsById(model.getId())) {
            throw new HttpBadRequestException("Article entity does not exist for id: " + model.getId());
        }

        final Article article = articleConverter.convertToEntity(model);
        final ArticleModel updated = articleConverter.convertToModel(articleRepository.save(article));

        log.info("Update Article END: {}", updated);
        return updated;
    }

    @Override
    public ArticleModel getById(long id) {
        log.info("Get article by id BEGIN: {}", id);

        final Optional<Article> articleOpt = articleRepository.findById(id);

        ArticleModel article = null;
        if (articleOpt.isPresent()) {
            article = articleConverter.convertToModel(articleOpt.get());
        }

        log.info("Get article by id END: {}", id, article);

        return article;
    }


    @Override
    public List<ArticleModel> getAllArticles() {
        log.info("Get all articles BEGIN: ");

        final List< Article> all = articleRepository.findAll();
        final List< ArticleModel> movies = articleConverter.convertToModels(all);

        log.info("Get all articles END: {}", movies);

        return movies;
    }
}
