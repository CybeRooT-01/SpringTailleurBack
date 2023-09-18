package com.gestion.tailleur.mapper;

import com.gestion.tailleur.Models.ArticleVente;
import com.gestion.tailleur.dto.response.ArticleVenteDTOresponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ArticleVenteDTOmapper implements Function<ArticleVente, ArticleVenteDTOresponse> {
    @Override
    public ArticleVenteDTOresponse apply(ArticleVente articleVente) {
        return new ArticleVenteDTOresponse(
                articleVente.getId(),
                articleVente.getLibelle(),
                articleVente.getImage(),
                articleVente.getReference(),
                articleVente.getPrix_vente(),
                articleVente.getMarge(),
                articleVente.getCout_fabrication(),
                articleVente.getCategorie(),
                articleVente.getArticleConfs()
        );
    }
}
