package com.aitinews.articles.rest;

import com.aitinews.articles.models.ArticleModel;
import com.aitinews.articles.service.ArticleService;
import com.aitinews.users.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleAccessValidator articleAccessValidator;
    private final UserService userService;

    public ArticleController(final ArticleService articleService,
                             final ArticleAccessValidator articleAccessValidator,
                             final UserService userService) {
        this.articleService = articleService;
        this.articleAccessValidator = articleAccessValidator;
        this.userService = userService;
    }

    @PostMapping
    public ArticleModel createArticle(@RequestBody final ArticleModel Article) {
        return articleService.createArticle(Article);
    }

    @GetMapping("/all")
    public List<ArticleModel> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PutMapping
    public ArticleModel updateArticle(@RequestBody final ArticleModel Article) {
        return articleService.updateArticle(Article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable final Long id) {
        articleService.deleteArticle(id);
    }
}
