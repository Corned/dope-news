package dopenews.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dopenews.domain.Category;
import dopenews.domain.Article;
import dopenews.domain.Picture;
import dopenews.domain.Writer;
import dopenews.repository.CategoryRepository;
import dopenews.repository.ArticleRepository;
import dopenews.repository.PictureRepository;
import dopenews.repository.WriterRepository;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        return "index";
    }

    @GetMapping("/luo")
    public String luoGET() {
        return "luo";
    }

    @PostMapping("/luo")
    public String luoPOST(@RequestParam("headline") String headline_, @RequestParam("lead") String lead_,
            @RequestParam("picture") MultipartFile picture_, @RequestParam("body") String body_,
            @RequestParam("writers") List<String> writers_, @RequestParam("categories") List<String> categories_) throws IOException {

        Picture picture = new Picture();
        picture.setData(picture_.getBytes());
        picture.setMediaType(picture_.getContentType());
        picture.setContentLength(picture_.getSize());
        pictureRepository.save(picture);

        Article article = new Article();

        List<Writer> writers = new ArrayList();
        for (int index = 0; index < writers_.size(); index++) {
            String writerName = writers_.get(index).toLowerCase();
            if (writerName.isEmpty()) continue;

            Writer writer = writerRepository.findOneByName(writerName);
            if (writer == null) {
                writer = new Writer();
                writer.setName(writerName);
                writerRepository.saveAndFlush(writer);
            }

            writers.add(writer);
        }

        List<Category> categories = new ArrayList();
        for (int index = 0; index < categories_.size(); index++) {
            String categoryName = categories_.get(index).toLowerCase();
            if (categoryName.isEmpty()) continue;

            Category category = categoryRepository.findOneByCategory(categoryName);
            if (category == null) {
                category = new Category();
                category.setCategory(categoryName);
                categoryRepository.saveAndFlush(category);
            }

            categories.add(category);
        }

        article.setHeadline(headline_);
        article.setLead(lead_);
        article.setPicture(picture);
        article.setBody(body_);
        article.setWriters(writers);
        article.setCategories(categories);
        article.setDate(new Date());

        articleRepository.saveAndFlush(article);

        for (Category category : categories) {
            category.addArticle(article);
            categoryRepository.save(category);
        }

        for (Writer writer : writers) {
            writer.addArticle(article);
            writerRepository.save(writer);
        }

        categoryRepository.flush();
        writerRepository.flush();

        return "redirect:/"; // to Article id
    }

    @GetMapping("/article/{id}")
    public String showArticle(Model model, @PathVariable long id) {
        model.addAttribute("article", articleRepository.findOne(id));

        return "article";
    }
}