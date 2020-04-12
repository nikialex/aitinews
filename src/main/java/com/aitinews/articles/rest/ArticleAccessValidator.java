package com.aitinews.articles.rest;

import com.aitinews.articles.models.ArticleModel;
import com.aitinews.articles.service.ArticleService;
import com.aitinews.exceptions.HttpForbiddenException;
import com.aitinews.users.entities.User;
import com.aitinews.users.service.UserService;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ArticleAccessValidator {

    private final ArticleService ArticleService;
    private final UserService userService;

    public ArticleAccessValidator(final com.aitinews.articles.service.ArticleService ArticleService, final UserService userService) {
        this.ArticleService = ArticleService;
        this.userService = userService;
    }

    public void validateUserArticleEdit(final String userId, final long ArticleId) {
        if (isNull(userId) || isNull(ArticleId)) {
            throw new HttpForbiddenException();
        }

        final ArticleModel article = ArticleService.getById(ArticleId);
        final User user = userService.getUserById(userId);

        if (user == null) {
            final String message = String
                    .format("Article with title " + article.getTitle() + " cannot be edited if you are not registered");
            throw new HttpForbiddenException(message);
        }
    }
}
