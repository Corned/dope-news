package dopenews.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Category extends AbstractPersistable<Long> {
    private String kategoria;
    @ManyToMany
    private List<News> uutiset;

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