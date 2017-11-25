package dopenews.repository;

import dopenews.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findOneByKategoria(String kategoria);
}