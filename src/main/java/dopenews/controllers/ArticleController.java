package dopenews.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dopenews.domain.Article;
import dopenews.services.CreationService;
import dopenews.services.ArticleService;

@Controller
public class ArticleController {
    @Autowired
    private CreationService creationService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String root() {
        return "redirect:/ajankohtaista";
    }
    
    @GetMapping("/ajankohtaista")
    public String listRecent(Model model) {
        List<Article> articles = articleService.findAll();
        articles = articleService.sortByDate(articles);
        articles = articleService.getPage(articles, 0, 5);

        model.addAttribute("articles", articles);
        model.addAttribute("category", "ajankohtaista");
        return "listaus";
    }
    
    @GetMapping("/luetuimmat")
    public String listPopular(Model model) {
        List<Article> articles = articleService.findAll();
        articles = articleService.filterArticlesWithNoViews(articles);
        articles = articleService.sortByPopularity(articles);
        articles = articleService.getPage(articles, 0, 50);

        model.addAttribute("articles", articles);
        model.addAttribute("category", "luetuimmat");
        return "listaus";
    }
    
    @GetMapping("/c/{category}")
    public String listCategory(Model model, @PathVariable String category) {

        model.addAttribute("category", category);
        return "listaus";
    }

    @GetMapping("/create")
    public String luoGET() {
        return "luo";
    }

    /*
            @Valid Article article, BindingResult bindingResult ei toimi.
            Teen oman validaattorin.
    */
    @PostMapping("/create")
    public String luoPOST(@RequestParam("headline") String headline, @RequestParam("lead") String lead,
            @RequestParam("picture") MultipartFile imageFile, @RequestParam("body") String body,
            @RequestParam("writers") List<String> writerNames, @RequestParam("categories") List<String> categoryNames) throws IOException {

        Article article = creationService.fromInput(
            headline, 
            lead, 
            body, 
            creationService.fromInput(imageFile), 
            writerNames, 
            categoryNames
        );

        return "redirect:/article/" + article.getId(); // to Article id
    }

    @GetMapping("/article/{id}")
    public String showArticle(Model model, @PathVariable long id) {
        Article article = articleService.findOne(id);
        articleService.incrementPageViews(article);

        model.addAttribute("article", article);

        System.out.println(article.getViews().size());

        return "article";
    }
}