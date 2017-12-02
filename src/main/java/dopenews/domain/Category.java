package dopenews.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

@Entity
public class Category extends AbstractPersistable<Long> {
    private String category;
    @ManyToMany(mappedBy = "categories")
    private List<Article> articles;

    public Category() {
        articles = new ArrayList();
    }

    public String getCategory() {
        return category;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Transactional
    public void setCategory(String category) {
        this.category = category;
    }

    @Transactional
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    
    @Transactional
    public void addArticle(Article article) {
        articles.add(article);
    }
}