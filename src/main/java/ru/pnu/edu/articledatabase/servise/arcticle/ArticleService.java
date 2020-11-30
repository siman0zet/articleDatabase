package ru.pnu.edu.articledatabase.servise.arcticle;

import ru.pnu.edu.articledatabase.entity.Article;
import ru.pnu.edu.articledatabase.servise.arcticle.argument.ArticleCreateArgument;
import ru.pnu.edu.articledatabase.servise.arcticle.argument.ArticleUpdateArgument;

import java.util.List;

public interface ArticleService {
    Article create(ArticleCreateArgument argument);

    void delete(Integer id);

    Article update(Integer id, ArticleUpdateArgument argument);

    List<Article> getAll();
}
