package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.ArticleFournisseur;
import com.gestion.tailleur.Models.Categories;
import com.gestion.tailleur.Models.Fournisseur;
import com.gestion.tailleur.repositories.ArticleConfRepository;
import com.gestion.tailleur.repositories.ArticleFournisseursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleConfService {
    private final ArticleConfRepository articleConfRepository;
    private final ArticleFournisseursRepository articleFournisseursRepository;

    public void creer(ArticleConf articleConf) {
        String libelle = articleConf.getLibelle();
        int prix = articleConf.getPrix();
        int stock = articleConf.getStock();
        String image = articleConf.getImage();
        String reference = articleConf.getReference();
        int id_categorie = articleConf.getCategorie().getId();
        ArticleConf nouvelArticle  = new ArticleConf();
        Categories categorie = new Categories();
        nouvelArticle.setLibelle(libelle);
        nouvelArticle.setPrix(prix);
        nouvelArticle.setStock(stock);
        nouvelArticle.setImage(image);
        nouvelArticle.setReference(reference);
        categorie.setId(id_categorie);
        nouvelArticle.setCategorie(categorie);
        this.articleConfRepository.save(nouvelArticle);
        for (Fournisseur fournisseur : articleConf.getFournisseurs()) {
            ArticleFournisseur articleFournisseur = new ArticleFournisseur();
            articleFournisseur.setArticleConf(nouvelArticle);
            articleFournisseur.setFournisseur(fournisseur);
            this.articleFournisseursRepository.save(articleFournisseur);
        }
    }
    public List<ArticleConf> getAll() {
        return this.articleConfRepository.findAll();
    }

    public void modifier(int id, ArticleConf articleConf) {
        ArticleConf articleConfAModifier = this.articleConfRepository.findById(id).get();
        articleConfAModifier.setLibelle(articleConf.getLibelle());
        articleConfAModifier.setPrix(articleConf.getPrix());
        articleConfAModifier.setStock(articleConf.getStock());
        articleConfAModifier.setImage(articleConf.getImage());
        articleConfAModifier.setReference(articleConf.getReference());
        articleConfAModifier.setCategorie(articleConf.getCategorie());
        this.articleConfRepository.save(articleConfAModifier);
        List<ArticleFournisseur> articleFournisseurs = this.articleFournisseursRepository.findAllByArticleConf(articleConfAModifier);
        for (ArticleFournisseur articleFournisseur : articleFournisseurs) {
            this.articleFournisseursRepository.delete(articleFournisseur);
        }
        for (Fournisseur fournisseur : articleConf.getFournisseurs()) {
            ArticleFournisseur articleFournisseur = new ArticleFournisseur();
            articleFournisseur.setArticleConf(articleConfAModifier);
            articleFournisseur.setFournisseur(fournisseur);
            this.articleFournisseursRepository.save(articleFournisseur);
        }


    }

    public ArticleConf getOneById(int id) {
        return this.articleConfRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        ArticleConf articleConf = this.articleConfRepository.findById(id).get();
        List<ArticleFournisseur> articleFournisseurs = this.articleFournisseursRepository.findAllByArticleConf(articleConf);
        this.articleFournisseursRepository.deleteAll(articleFournisseurs);
        this.articleConfRepository.deleteById(id);
    }
}
