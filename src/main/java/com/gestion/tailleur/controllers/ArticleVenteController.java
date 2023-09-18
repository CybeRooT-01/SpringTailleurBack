package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.ArticleVente;
import com.gestion.tailleur.dto.requests.ArticleVenteDTOrequest;
import com.gestion.tailleur.dto.response.ArticleConfDTOresponse;
import com.gestion.tailleur.dto.response.ArticleVenteDTOresponse;
import com.gestion.tailleur.services.ArticleVenteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/articleVente")
@AllArgsConstructor
public class ArticleVenteController {
    private final ArticleVenteService articleVenteService;

    @GetMapping
    public Stream<ArticleVenteDTOresponse> listerArticles() {
        return this.articleVenteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleVenteDTOresponse> getOneById(@PathVariable int id) {
        ArticleVenteDTOresponse articleVente = this.articleVenteService.getOneById(id);
        if (articleVente == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }
        int status = 200;
        return ResponseEntity.status(status).body(articleVente);
    }

    @PostMapping
    public ArticleVenteDTOresponse ajouterArticleVente(@RequestBody ArticleVenteDTOrequest articleVente) {
        return this.articleVenteService.ajouterArticleVente(articleVente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleVenteDTOresponse> updateArticleVente(@PathVariable int id, @RequestBody ArticleVenteDTOrequest articleVente) {
        ArticleVenteDTOresponse articleAmodifier = this.articleVenteService.getOneById(id);
        if (articleAmodifier == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }
        ArticleVenteDTOresponse response = this.articleVenteService.modifier(id, articleVente);
        int status = 200;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteArticle(@PathVariable Integer id){
        ArticleVenteDTOresponse articleAmodifier = this.articleVenteService.getOneById(id);
        if (articleAmodifier == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }

        this.articleVenteService.supprimer(id);
        String message = "Suppression effectuer avec succes";
        return ResponseEntity.status(200).body(message);


    }


}
