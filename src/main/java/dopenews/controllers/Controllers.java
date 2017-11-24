package dopenews.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dopenews.repository.NewsRepository;
import dopenews.domain.News;

@Controller
public class Controllers {
    @Autowired
    private NewsRepository newsRepository;


    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("news", newsRepository.findAll());
        return "index";
    }

    @PostMapping("/")
    public String save(@RequestParam("title") String title) {
        News news = new News();
        news.setTitle(title);

        System.out.println(title);

        newsRepository.saveAndFlush(news);
        return "redirect:/";
    }
}