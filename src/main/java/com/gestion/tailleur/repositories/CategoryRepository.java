package com.gestion.tailleur.repositories;

import com.gestion.tailleur.entities.Categories;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    List<Categories>findByLibelle(String libelle);
}
