package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.ArticleVente;
import com.gestion.tailleur.dto.requests.ArticleVenteDTOrequest;
import com.gestion.tailleur.dto.response.ArticleVenteDTOresponse;
import com.gestion.tailleur.mapper.ArticleVenteDTOmapper;
import com.gestion.tailleur.repositories.ArticleConfRepository;
import com.gestion.tailleur.repositories.ArticleVenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ArticleVenteService {
    private final ArticleVenteRepository articleVenteRepository;
    private final ArticleConfRepository articleConfRepository;
    private final ArticleVenteDTOmapper articleVenteDTOmapper;

    public ArticleVenteDTOresponse ajouterArticleVente(ArticleVenteDTOrequest articleVente) {
         ArticleVente newArticleVente = new ArticleVente();
         newArticleVente.setLibelle(articleVente.libelle());
            newArticleVente.setImage(articleVente.image());
            newArticleVente.setReference(articleVente.reference());
            newArticleVente.setPrix_vente(articleVente.prix_vente());
            newArticleVente.setMarge(articleVente.marge());
            newArticleVente.setCout_fabrication(articleVente.cout_fabrication());
            newArticleVente.setCategorie(articleVente.categories());
            newArticleVente.getArticleConfs()
                    .addAll(articleVente
                            .articleConfs()
                            .stream()
                            .map(c ->{
                                ArticleConf ac = articleConfRepository.findById(c.getId());
                                ac.getArticleVentes().add(newArticleVente);
                                return  ac;
                            }).collect(Collectors.toList()));
            articleVenteRepository.save(newArticleVente);
            return articleVenteDTOmapper.apply(newArticleVente);
    }

    public Stream<ArticleVenteDTOresponse> getAll() {
        return this.articleVenteRepository.findAll()
                .stream()
                .map(articleVenteDTOmapper);
    }

    public ArticleVenteDTOresponse getOneById(int id) {
        ArticleVente articleVente = this.articleVenteRepository.findById(id);
        return articleVenteDTOmapper.apply(articleVente);
    }

    public ArticleVenteDTOresponse modifier(int id, ArticleVenteDTOrequest articleVente) {
        ArticleVente articleVente1 = this.articleVenteRepository.findById(id);
        articleVente1.setLibelle(articleVente.libelle());
        articleVente1.setImage(articleVente.image());
        articleVente1.setReference(articleVente.reference());
        articleVente1.setPrix_vente(articleVente.prix_vente());
        articleVente1.setMarge(articleVente.marge());
        articleVente1.setCout_fabrication(articleVente.cout_fabrication());
        articleVente1.setCategorie(articleVente.categories());
        articleVente1.setArticleConfs(
                articleVente.articleConfs()
                        .stream()
                        .map(c ->{
                            ArticleConf ac = articleConfRepository.findById(c.getId());
                            ac.getArticleVentes().add(articleVente1);
                            return  ac;
                        }).collect(Collectors.toList())
        );
        this.articleVenteRepository.save(articleVente1);
        return this.articleVenteDTOmapper.apply(articleVente1);
    }

    public void supprimer(Integer id) {
        this.articleVenteRepository.deleteById(id);
    }
}
