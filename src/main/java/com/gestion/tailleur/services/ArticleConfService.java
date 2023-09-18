package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.ArticleFournisseur;
import com.gestion.tailleur.Models.Fournisseur;
import com.gestion.tailleur.dto.requests.ArticleConfDTOrequest;
import com.gestion.tailleur.dto.response.ArticleConfDTOresponse;
import com.gestion.tailleur.mapper.ArticleConfDTOmapper;
import com.gestion.tailleur.repositories.ArticleConfRepository;
import com.gestion.tailleur.repositories.ArticleFournisseursRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ArticleConfService {
    private final ArticleConfRepository articleConfRepository;
    private final ArticleFournisseursRepository articleFournisseursRepository;
    private final ArticleConfDTOmapper articleConfDTOmapper;

    public ArticleConfDTOresponse creer(ArticleConfDTOrequest articleConf) {


        ArticleConf articleConf1 = ArticleConf.builder()
                .libelle(articleConf.libelle())
                .prix(articleConf.prix())
                .stock(articleConf.stock())
                .image(articleConf.image())
                .reference(articleConf.reference())
                .categorie(articleConf.categories())
//                .fournisseurs(articleConf.fournisseurs())
                .build();

        articleConf1.setCategorie(articleConf.categories());
        this.articleConfRepository.save(articleConf1);
        for (Fournisseur fournisseur : articleConf.fournisseurs()) {
            ArticleFournisseur articleFournisseur = new ArticleFournisseur();
            articleFournisseur.setArticleConf(articleConf1);
            articleFournisseur.setFournisseur(fournisseur);
            this.articleFournisseursRepository.save(articleFournisseur);
        }
        return articleConfDTOmapper.apply(articleConf1);
    }
    public Stream<ArticleConfDTOresponse> getAll() {
        return this.articleConfRepository.findAll()
                .stream()
                .map(articleConfDTOmapper);
    }

    public ArticleConfDTOresponse modifier(int id, ArticleConfDTOrequest articleConf) {
        ArticleConf articleConf1 = this.articleConfRepository.findById(id);
        articleConf1.setLibelle(articleConf.libelle());
        articleConf1.setPrix(articleConf.prix());
        articleConf1.setStock(articleConf.stock());
        articleConf1.setImage(articleConf.image());
        articleConf1.setReference(articleConf.reference());
        articleConf1.setCategorie(articleConf.categories());
        this.articleConfRepository.save(articleConf1);
        int articleConfId = articleConf1.getId();
        List<ArticleFournisseur> articleFournisseurs = this.articleFournisseursRepository.findAll();
        this.articleFournisseursRepository.deleteAll(articleFournisseurs);
        for (Fournisseur fournisseur : articleConf.fournisseurs()) {
            ArticleFournisseur articleFournisseur = new ArticleFournisseur();
            articleFournisseur.setArticleConf(articleConf1);
            articleFournisseur.setFournisseur(fournisseur);
            this.articleFournisseursRepository.save(articleFournisseur);
        }

        return this.articleConfDTOmapper.apply(articleConf1);
    }

    public ArticleConfDTOresponse getOneById(int id) {
        ArticleConf article = this.articleConfRepository.findById(id);
        return this.articleConfDTOmapper.apply(article);
    }


    public void delete(Integer id) {
        ArticleConf articleConf = this.articleConfRepository.findById(id).get();
//        System.out.println("Type de l'ID : " + ((Object) id).getClass().getName());
        this.articleConfRepository.delete(articleConf);
    }
}
