package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    List<Categories>findByLibelle(String libelle);

    //RepositoryRestRessourse = genere le controller et le crud (pour formater les donnés on tuilise les projection)

    //@RestContorller pour redefinir les endpoints du controller generer par RepositoryRestRessourse(donnant la mm path)

    /**
     *Creer des dtos pour que dans le service, par exemple pour la methode creer, j'utilise pas l'article(pas l'entite) mais plutot la dto request
     *
     */
}
