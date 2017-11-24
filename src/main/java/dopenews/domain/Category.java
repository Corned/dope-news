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
public class Category extends AbstractPersistable<Long> {
    private String kategoria;
    @ManyToMany
    private List<News> uutiset;
}