package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.ArticleVente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path = "articleVente")
public interface ArticleVenteRepository extends JpaRepository<ArticleVente, Integer>{
    ArticleVente findByLibelle(String libelle);
    ArticleVente findById(int id);
}
