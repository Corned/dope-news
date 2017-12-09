package dopenews.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.transaction.annotation.Transactional;

@Entity
public class Article extends AbstractPersistable<Long> {
    @NotEmpty
    @Size(min = 3, max = 255)
    private String headline;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String lead;
    
    @OneToOne
    @NotNull
    private Picture picture;

    @NotEmpty
    @Size(min = 3, max = 2500)
    private String body;
    
    @NotNull
    private LocalDateTime date;

    @NotEmpty
    @ManyToMany
    private List<Writer> writers;

    @NotEmpty
    @ManyToMany
    private List<Category> categories;

    @Min(0)
    private int views;

    public String getHeadline() {
        return headline;
    }
    
    public String getLead() {
        return lead;
    }
    
    public Picture getPicture() {
        return picture;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public int getViews() {
        return views;
    }

    
    @Transactional
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    @Transactional
    public void setLead(String lead) {
        this.lead = lead;
    }
    
    @Transactional
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Transactional
    public void setBody(String body) {
        this.body = body;
    }

    @Transactional
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Transactional
    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    @Transactional
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Transactional
    public void setViews(int views) {
        this.views = views;
    }

    @Transactional
    public void incremenetView() {
        this.views++;
    }
}