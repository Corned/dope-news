package dopenews.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class News extends AbstractPersistable<Long> {
    private String otsikko;
    private String ingressi;
    @OneToOne
    private Picture kuva;
    private String leipateksti;
    private Date julkaisuaika;
    @ManyToMany(mappedBy = "uutiset")
    private List<Writer> kirjoittajat;
    //@ManyToMany(mappedBy = "uutiset")
    //private List<Category> kategoriat;
}