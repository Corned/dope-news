package dopenews.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long> {
    private String kategoria;
    @ManyToMany(mappedBy = "kategoriat")
    private List<News> uutiset;

    public Category() {
        uutiset = new ArrayList();
    }

    public void addUutinen(News news) {
        uutiset.add(news);
    }

    public String getKategoria() {
        return kategoria;
    }

    public List<News> getUutiset() {
        return uutiset;
    }


    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public void setUutiset(List<News> uutiset) {
        this.uutiset = uutiset;
    }
}