package dopenews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dopenews.repository.NewsRepository;
import dopenews.domain.*;

@Controller
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;


    @GetMapping("/")
    public String list(Model model) {
        return "index";
    }

    @GetMapping("/luo")
    public String luoGET() {
        return "luo";
    }

    @PostMapping("/luo")
    public String luoPOST(@RequestParam String otsikko, @RequestParam String ingressi, 
            @RequestParam MultipartFile kuva, @RequestParam String leipateksti, 
            @RequestParam List<String> kirjoittajat, @RequestParam List<String> kategoriat) {

        System.out.println(otsikko);
        System.out.println(ingressi);
        System.out.println(kuva.getOriginalFilename());
        System.out.println(leipateksti);
        System.out.println(kirjoittajat.size());
        System.out.println(kategoriat.size());
        return "redirect:/"; // to news id
    }
}