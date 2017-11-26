package dopenews.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Writer extends AbstractPersistable<Long> {
    private String name;
    @ManyToMany(mappedBy = "writers")
    private List<Article> articles;

    public Writer() {
        articles = new ArrayList();
    }

    public void addArticle(Article article) {
        articles.add(article);
    }

    public String getName() {
        return name;
    }

    public List<Article> getArticles() {
        return articles;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}