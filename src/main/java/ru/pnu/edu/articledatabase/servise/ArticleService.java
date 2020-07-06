package ru.pnu.edu.articledatabase.servise;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import ru.pnu.edu.articledatabase.entity.Article;
import ru.pnu.edu.articledatabase.servise.HibernateUtil;

public class ArticleService {

    public void save(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession(); //открываем сессию
        session.beginTransaction();
        session.save(article); //пользуемся ее методами
        session.flush();
        session.close();
    }

    public void delete(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(article);
        session.flush();
        session.close();
    }

    public List<Article> getAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createCriteria(Article.class).list();
    }

    public Article getById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Article article = session.get(Article.class, id);
        return article;
    }

}