package dopenews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dopenews.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(value = "SELECT * FROM Article a INNER JOIN Article_Categories ac ON a.id = ac.articles_id INNER JOIN Category c ON ac.categories_id = c.id WHERE c.category LIKE %?1", nativeQuery=true)
    List<Article> findByCategory(String category);
}