package ru.pnu.edu.articledatabase.servise.arcticle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.pnu.edu.articledatabase.entity.Article;
import ru.pnu.edu.articledatabase.repository.ArticleRepository;
import ru.pnu.edu.articledatabase.servise.arcticle.argument.ArticleCreateArgument;
import ru.pnu.edu.articledatabase.servise.arcticle.argument.ArticleUpdateArgument;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository repository;

    @Override
    @Transactional
    public Article create(ArticleCreateArgument argument) {
        return repository.save(Article.builder()
                .authors(argument.getAuthors())
                .name(argument.getName())
                .article_level(argument.getArticle_level())
                .stage(argument.getStage())
                .magazine_id(argument.getMagazine_id())
                .page_range(argument.getPage_range())
                .volume(argument.getVolume())
                .departure_date(argument.getDeparture_date())
                .science_area(argument.getScience_area())
                .price(argument.getPrice())
                .release(argument.getRelease())
                .study_year(argument.getStudy_year())
                .publication_type(argument.getPublication_type())
                .export_control_id(argument.getExport_control_id())
                .open_publication_id(argument.getOpen_publication_id())
                .authors(argument.getAuthors())
                .build());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Integer id) {
        repository.delete(repository.getOne(id));
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Article update(Integer id, ArticleUpdateArgument argument) {
        Article article = repository.getOne(id);
        article.setName(argument.getName());
        article.setArticle_level(argument.getArticle_level());
        article.setAuthors(argument.getAuthors());
        article.setDeparture_date(argument.getDeparture_date());
        article.setExport_control_id(argument.getExport_control_id());
        article.setOpen_publication_id(argument.getOpen_publication_id());
        article.setRelease(argument.getRelease());
        article.setMagazine_id(argument.getMagazine_id());
        article.setPublication_type(argument.getPublication_type());
        article.setStage(argument.getStage());
        article.setScience_area(argument.getScience_area());
        article.setStudy_year(argument.getStudy_year());
        article.setPage_range(argument.getPage_range());
        article.setVolume(argument.getVolume());
        article.setPrice(argument.getPrice());
        return repository.save(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Article> getAll() {
        return repository.findAll();
    }
}
