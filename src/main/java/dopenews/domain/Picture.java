package dopenews.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Picture extends AbstractPersistable<Long> {
    private News uutiset;
    private String nimi;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;

    public News getUutiset() {
        return uutiset;
    }

    public String getNimi() {
        return nimi;
    }

    public byte[] getData() {
        return data;
    }


    public void setUutiset(News uutiset) {
        this.uutiset = uutiset;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}