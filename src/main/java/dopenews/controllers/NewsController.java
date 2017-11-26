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
import dopenews.domain.News;
import dopenews.domain.Picture;
import dopenews.domain.Writer;
import dopenews.repository.CategoryRepository;
import dopenews.repository.NewsRepository;
import dopenews.repository.PictureRepository;
import dopenews.repository.WriterRepository;

@Controller
public class NewsController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("news", newsRepository.findAll());
        return "index";
    }

    @GetMapping("/luo")
    public String luoGET() {
        return "luo";
    }

    @PostMapping("/luo")
    public String luoPOST(@RequestParam String otsikko, @RequestParam String ingressi,
            @RequestParam MultipartFile kuva, @RequestParam String leipateksti,
            @RequestParam List<String> kirjoittajat, @RequestParam List<String> kategoriat) throws IOException {

        Picture picture = new Picture();
        picture.setData(kuva.getBytes());
        picture.setMediaType(kuva.getContentType());
        picture.setContentLength(kuva.getSize());
        pictureRepository.save(picture);

        News news = new News();

        List<Writer> writers = new ArrayList();
        for (int index = 0; index < kirjoittajat.size(); index++) {
            String kirjoittajaNimi = kirjoittajat.get(index).toLowerCase();
            if (kirjoittajaNimi.isEmpty()) continue;

            Writer writer = writerRepository.findOneByNimi(kirjoittajaNimi);
            System.out.println(writer);
            if (writer == null) {
                System.out.println("NEW!");
                writer = new Writer();
                writer.setNimi(kirjoittajaNimi);
                writerRepository.saveAndFlush(writer);
            } else {
                System.out.println("old writer!");
                System.out.println(writer.getNimi());
                System.out.println(writer.getUutiset().size() + " uutista");
            }

            writers.add(writer);
        }

        List<Category> categories = new ArrayList();
        for (int index = 0; index < kategoriat.size(); index++) {
            String kategoriaNimi = kategoriat.get(index).toLowerCase();
            if (kategoriaNimi.isEmpty()) continue;

            Category category = categoryRepository.findOneByKategoria(kategoriaNimi);
            if (category == null) {
                category = new Category();
                category.setKategoria(kategoriaNimi);
                categoryRepository.saveAndFlush(category);
            }

            categories.add(category);
        }

        news.setOtsikko(otsikko);
        news.setIngressi(ingressi);
        news.setKuva(picture);
        news.setLeipateksti(leipateksti);
        news.setKirjoittajat(writers);
        news.setKategoriat(categories);
        news.setJulkaisuaika(new Date());

        newsRepository.saveAndFlush(news);

        for (Category c : categories) {
            c.addUutinen(news);
            categoryRepository.save(c);
        }

        for (Writer w : writers) {
            w.addUutinen(news);
            writerRepository.save(w);
        }

        categoryRepository.flush();
        writerRepository.flush();

        return "redirect:/"; // to news id
    }

    @GetMapping("/article/{id}")
    public String showArticle(Model model, @PathVariable long id) {
        model.addAttribute("article", newsRepository.findOne(id));

        return "article";
    }
}