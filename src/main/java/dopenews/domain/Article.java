package dopenews.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Article extends AbstractPersistable<Long> {
    private String headline;
    private String lead;
    @OneToOne
    private Picture picture;
    private String body;
    private Date date;
    @ManyToMany
    private List<Writer> writers;
    @ManyToMany
    private List<Category> categories;
    
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

    public Date getDate() {
        return date;
    }

    public List<Writer> getWriters() {
        return writers;
    }

    public List<Category> getCategories() {
        return categories;
    }

    
    
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public void setLead(String lead) {
        this.lead = lead;
    }
    
    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}