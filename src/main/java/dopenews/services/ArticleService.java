package dopenews.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dopenews.domain.Article;
import dopenews.domain.View;
import dopenews.repository.ArticleRepository;
import dopenews.repository.ViewRepository;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ViewRepository viewRepository;
    private Comparator date;
    private Comparator popularity;
    private long weekInMilliseconds = 604800000;
    private long thirtySecondsInMilliseconds = 30000;

    public ArticleService() {
        this.date = new Comparator<Article>() {
            public int compare(Article a, Article b) {
                return (int)(b.getPublished().getTime() - a.getPublished().getTime());
            }
        };

        this.popularity = new Comparator<Article>() {
            public int compare(Article a, Article b) {
                long timeNow = new Date().getTime();
                int aViewsPastTwoWeeks = 0;
                int bViewsPartTwoWeeks = 0;
                
                for (int i = 0; i < a.getViews().size(); i++) {
                    View view = a.getViews().get(i);
                    Date date = view.getDate();
                    if (date.getTime() + weekInMilliseconds > timeNow) {
                        aViewsPastTwoWeeks++;
                    }
                }
                
                for (int i = 0; i < b.getViews().size(); i++) {
                    View view = b.getViews().get(i);
                    Date date = view.getDate();
                    if (date.getTime() + weekInMilliseconds > timeNow) {
                        bViewsPartTwoWeeks++;
                    }
                }
                return (bViewsPartTwoWeeks - aViewsPastTwoWeeks);
            }
        };
    }
    
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findOne(long id) {
        return articleRepository.findOne(id);
    }

    public List<Article> findAllByCategory(String categoryName) {
        List<Article> articles = articleRepository.findByCategory(categoryName);
        return articles;
    }

    public List<Article> getPage(List<Article> articles, int index, int amount) {
        List<Article> output = new ArrayList<>();
        
        if (articles.size() == 0) {
            return output;
        }

        for (int i = index; i < index + amount; i++) {
            if (articles.size() > i) {
                output.add(articles.get(i));
            }
        }

        return output;
    }

    public List<Article> filterArticlesWithNoViews(List<Article> articles) {
        long timeNow = new Date().getTime();
        for (int articleIndex = articles.size() - 1; articleIndex > -1; articleIndex--) {
            Article article = articles.get(articleIndex);
            List<View> views = article.getViews();
            int validViews = 0;
            for (int viewIndex = views.size() - 1; viewIndex > -1; viewIndex--) {
                View view = views.get(viewIndex);
                Date date = view.getDate();

                if (date.getTime() + weekInMilliseconds < timeNow) {
                    // View is stale, remove
                    views.remove(viewIndex);
                    viewRepository.delete(view);
                } else {
                    validViews++;
                }
            }

            if (validViews == 0) {
                // Article has no valid views
                articles.remove(articleIndex);
                System.out.println("Article has no valid views! " + article.getHeadline());
            } else {
                System.out.println("Views! " + article.getHeadline());
            }
        }

        /*
        viewRepository.saveAndFlush();
        articleRepository.saveAndFlush();
*/
        return articles;
    }
    
    public List<Article> sortByDate(List<Article> articles) {
        Collections.sort(articles, this.date);
        return articles;
    }
    
    public List<Article> sortByPopularity(List<Article> articles) {
        Collections.sort(articles, this.popularity);
        return articles;
    }

    public void incrementPageViews(Article article) {
        View view = new View();
        view.setArticle(article);
        article.addView(view);
        
        viewRepository.save(view);
        articleRepository.save(article);

        System.out.println(view.getDate().getTime());
    }
}