package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.ArticleFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleFournisseursRepository extends JpaRepository<ArticleFournisseur, Integer> {
    List<ArticleFournisseur> findAllByArticleConf(ArticleConf articleConfAModifier);

    List<ArticleFournisseur> findByArticleConf(ArticleConf articleConf1);
}
