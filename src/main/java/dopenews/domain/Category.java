package dopenews.domain;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long> {
    private String kategoria;
    @ManyToMany
    private List<News> uutiset_;

    public Category() {
        uutiset_ = new ArrayList();
    }

    public void addUutinen(News news) {
        uutiset_.add(news);
    }

    public String getKategoria() {
        return kategoria;
    }

    public List<News> getuutiset_() {
        return uutiset_;
    }


    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public void setuutiset_(List<News> uutiset_) {
        this.uutiset_ = uutiset_;
    }
}