package dopenews.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Writer extends AbstractPersistable<Long> {
    private String name;
    @ManyToMany(mappedBy = "writers")
    private List<News> uutiset;
}