package dopenews.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class News extends AbstractPersistable<Long> {
    private String otsikko;
    private String ingressi;
    @OneToOne
    private Picture kuva;
    private String leipateksti;
    private Date julkaisuaika;
    @ManyToMany(mappedBy = "uutiset")
    private List<Writer> kirjoittajat;
    @ManyToMany(mappedBy = "uutiset")
    private List<Category> kategoriat;
    
    public String getOtsikko() {
        return otsikko;
    }
    
    public String getIngressi() {
        return ingressi;
    }
    
    public Picture getKuva() {
        return kuva;
    }

    public String leipateksti() {
        return leipateksti;
    }

    public Date getJulkaisuaika() {
        return julkaisuaika;
    }

    public List<Writer> getKirjoittajat() {
        return kirjoittajat;
    }

    public List<Category> getKategoriat() {
        return kategoriat;
    }

    
    
    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }
    
    public void setIngressi(String ingressi) {
        this.ingressi = ingressi;
    }
    
    public void setKuva(Picture kuva) {
        this.kuva = kuva;
    }

    public void setLeipateksti(String leipateksti) {
        this.leipateksti = leipateksti;
    }

    public void setJulkaisuaika(Date julkaisuaika) {
        this.julkaisuaika = julkaisuaika;
    }

    public void setKirjoittajat(List<Writer> kirjoittajat) {
        this.kirjoittajat = kirjoittajat;
    }

    public void setKategoriat(List<Category> kategoriat) {
        this.kategoriat = kategoriat;
    }
}