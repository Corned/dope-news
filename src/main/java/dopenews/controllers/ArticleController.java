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
import dopenews.domain.Picture;
import dopenews.repository.ArticleRepository;
import dopenews.services.CreationService;

@Controller
public class ArticleController {
    @Autowired
    private CreationService creationService;
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String listRecent(Model model) {
        model.addAttribute("category", "ajankohtaista");
        model.addAttribute("articles", articleRepository.findAll());

        return "listaus";
    }

    @GetMapping("/c")
    public String redirectListRecent() {
        return "redirect:/";
    }

    @GetMapping("/c/{category}")
    public String listCategory(Model model, @PathVariable String category) {
        model.addAttribute("category", category);
        model.addAttribute("articles", articleRepository.findByCategory(category));
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
        model.addAttribute("article", articleRepository.findOne(id));

        return "article";
    }
}