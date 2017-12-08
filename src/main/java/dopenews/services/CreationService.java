/* let there be light*/

package dopenews.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dopenews.domain.Article;
import dopenews.domain.Category;
import dopenews.domain.Picture;
import dopenews.domain.Writer;
import dopenews.repository.ArticleRepository;
import dopenews.repository.CategoryRepository;
import dopenews.repository.PictureRepository;
import dopenews.repository.WriterRepository;

@Service
public class CreationService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PictureRepository pictureRepository;
    @Autowired
    private WriterRepository writerRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Picture fromInput(MultipartFile file) throws IOException {
        Picture picture = new Picture();
        picture.setData(file.getBytes());
        picture.setMediaType(file.getContentType());
        picture.setContentLength(file.getSize());

        pictureRepository.saveAndFlush(picture);
        return picture;
    }

    public Article fromInput(String headline, String lead, String body, 
            Picture picture, List<String> writerStrings, List<String> categoryStrings) {

        List<Writer> writers = new ArrayList();
        for (int index = 0; index < writerStrings.size(); index++) {
            String writerName = writerStrings.get(index).toLowerCase();
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
        for (int index = 0; index < categoryStrings.size(); index++) {
            String categoryName = categoryStrings.get(index).toLowerCase();
            if (categoryName.isEmpty()) continue;

            Category category = categoryRepository.findOneByCategory(categoryName);
            if (category == null) {
                category = new Category();
                category.setCategory(categoryName);
                categoryRepository.saveAndFlush(category);
            }

            categories.add(category);
        }
                
        Article article = new Article();
        article.setHeadline(headline);
        article.setLead(lead);
        article.setPicture(picture);
        article.setBody(body);
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

        return article;
    }
}