package com.gestion.tailleur.repositories;

import com.gestion.tailleur.entities.ArticleConf;
import com.gestion.tailleur.entities.ArticleFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleFournisseursRepository extends JpaRepository<ArticleFournisseur, Integer> {
    List<ArticleFournisseur> findAllByArticleConf(ArticleConf articleConfAModifier);
}
