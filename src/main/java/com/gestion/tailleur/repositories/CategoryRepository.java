package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    List<Categories>findByLibelle(String libelle);
}
