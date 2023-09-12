package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.response.ArticleConfResponse;
import com.gestion.tailleur.services.ArticleConfService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/articleConf")
public class ArticleConfController {
    private final ArticleConfService articleConfService;

    @GetMapping()
    public List<ArticleConf> listerArticles() {
        List<ArticleConf> articles = this.articleConfService.getAll();
        return articles;
    }


    @PostMapping()
    public ResponseEntity<ArticleConfResponse> creerArticleVente(@RequestBody ArticleConf articleConf) {
        this.articleConfService.creer(articleConf);
        String message = "L'article a été créé avec succès.";
        int status = 201;
        ArticleConfResponse response = new ArticleConfResponse(message, status);
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleConfResponse> updateArticleVente(@PathVariable int id, @RequestBody ArticleConf articleConf) {
        ArticleConf articleAmodifier = this.articleConfService.getOneById(id);
        if (articleAmodifier == null) {
            String message = "L'article n'existe pas.";
            int status = 404;
            ArticleConfResponse response = new ArticleConfResponse(message, status);
            return ResponseEntity.status(status).body(response);
        }
        this.articleConfService.modifier(id, articleConf);
        String message = "L'article a été modifié avec succès.";
        int status = 200;
        ArticleConfResponse response = new ArticleConfResponse(message, status);
        return ResponseEntity.status(status).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ArticleConfResponse> deleteArticleVente(@PathVariable int id) {
        ArticleConf articleAmodifier = this.articleConfService.getOneById(id);
        if (articleAmodifier == null) {
            String message = "L'article n'existe pas.";
            int status = 404;
            ArticleConfResponse response = new ArticleConfResponse(message, status);
            return ResponseEntity.status(status).body(response);
        }
        this.articleConfService.delete(id);
        String message = "L'article a été supprimé avec succès.";
        int status = 200;
        ArticleConfResponse response = new ArticleConfResponse(message, status);
        return ResponseEntity.status(status).body(response);
    }


}
