package com.gestion.tailleur.mapper;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.Models.Fournisseur;
import com.gestion.tailleur.dto.response.ArticleConfDTOresponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
@Component
public class ArticleConfDTOmapper implements Function<ArticleConf, ArticleConfDTOresponse> {

    @Override
    public ArticleConfDTOresponse apply(ArticleConf articleConf) {
        return new ArticleConfDTOresponse(
                articleConf.getId(),
                articleConf.getLibelle(),
                articleConf.getPrix(),
                articleConf.getStock(),
                articleConf.getReference(),
                articleConf.getImage(),
                articleConf.getCategorie(),
                articleConf.getFournisseurs()
        );
    }
}
