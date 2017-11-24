package dopenews.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
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
    private Picture kuva;
    private String leipateksti;
    private Date julkaisuaika;
    private List<Writer> kirjoittajat;
}