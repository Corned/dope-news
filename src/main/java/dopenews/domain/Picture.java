package dopenews.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Picture extends AbstractPersistable<Long> {
    private News news;
    private String name;
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] data;
}