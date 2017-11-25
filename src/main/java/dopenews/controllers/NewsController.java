package dopenews.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dopenews.repository.CategoryRepository;
import dopenews.repository.NewsRepository;
import dopenews.repository.PictureRepository;
import dopenews.repository.WriterRepository;
import dopenews.domain.*;

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

    @ResponseBody
    @GetMapping(path = "/image/{id}", produces = "image/png")
    public byte[] getImage(@PathVariable long id) {
        return pictureRepository.findOne(id).getData();
    }

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
        
        List<Writer> writers = new ArrayList();
        for (int index = 0; index < kirjoittajat.size(); index++) {
            String kirjoittajaNimi = kirjoittajat.get(index).toLowerCase();
            if (kirjoittajaNimi.isEmpty()) continue;

            Writer writer = writerRepository.findOneByNimi(kirjoittajaNimi);
            if (writer == null) {
                writer = new Writer();
                writer.setNimi(kirjoittajaNimi);
                writerRepository.save(writer);
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
                categoryRepository.save(category);
            }

            categories.add(category);
        }

        News news = new News();
        news.setOtsikko(otsikko);
        news.setIngressi(ingressi);
        news.setKuva(picture);
        news.setLeipateksti(leipateksti);
        news.setKirjoittajat(writers);
        news.setKategoriat(categories);
        news.setJulkaisuaika(new Date());

        newsRepository.save(news);

        writerRepository.flush();
        categoryRepository.flush();
        pictureRepository.flush();
        newsRepository.flush();

        return "redirect:/"; // to news id
    }
}