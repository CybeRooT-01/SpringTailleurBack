package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.projections.CategorieProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "categories", path = "categorie", excerptProjection = CategorieProjection.class)
public interface CategoryRepository extends JpaRepository<Categories, Integer> {
    List<Categories>findByLibelle(String libelle);

    //RepositoryRestRessourse = genere le controller et le crud (pour formater les donnés on tuilise les projection)

    //@RestContorller pour redefinir les endpoints du controller generer par RepositoryRestRessourse(donnant la mm path)

    /**
     *Creer des dtos pour que dans le service, par exemple pour la methode creer, j'utilise pas l'article(pas l'entite) mais plutot la dto request
     *
     */
}
