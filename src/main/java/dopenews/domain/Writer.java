package dopenews.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Writer extends AbstractPersistable<Long> {
    private String nimi;
    @ManyToMany
    private List<News> uutiset;

    public String getNimi() {
        return nimi;
    }

    public List<News> getUutiset() {
        return uutiset;
    }


    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public void setUutiset(List<News> uutiset) {
        this.uutiset = uutiset;
    }
}