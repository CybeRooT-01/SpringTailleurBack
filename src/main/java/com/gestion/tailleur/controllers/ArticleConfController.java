package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.ArticleConf;
import com.gestion.tailleur.dto.requests.ArticleConfDTOrequest;
import com.gestion.tailleur.dto.response.ArticleConfDTOresponse;

import com.gestion.tailleur.repositories.ArticleConfRepository;
import com.gestion.tailleur.services.ArticleConfService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/articleConf")
public class ArticleConfController {
    private final ArticleConfService articleConfService;
    private final ArticleConfRepository articleConfRepository;
    @GetMapping()
    public Stream<ArticleConfDTOresponse> listerArticles() {
        return this.articleConfService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleConfDTOresponse> getOneById(@PathVariable int id) {
        ArticleConfDTOresponse articleConf = this.articleConfService.getOneById(id);
        if (articleConf == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }
        int status = 200;
        return ResponseEntity.status(status).body(articleConf);
    }


    @PostMapping()
    public ResponseEntity<ArticleConfDTOresponse> creerArticleVente(@RequestBody ArticleConfDTOrequest articleConf) {
       ArticleConfDTOresponse insered = this.articleConfService.creer(articleConf);
        return ResponseEntity.ok().body(insered);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleConfDTOresponse> updateArticleVente(@PathVariable int id, @RequestBody ArticleConfDTOrequest articleConf) {
        ArticleConfDTOresponse articleAmodifier = this.articleConfService.getOneById(id);
        if (articleAmodifier == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }
        ArticleConfDTOresponse response = this.articleConfService.modifier(id, articleConf);
        int status = 200;
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticleVente(@PathVariable Integer id) {
        ArticleConfDTOresponse articleAmodifier = this.articleConfService.getOneById(id);
        if (articleAmodifier == null) {
            int status = 404;
            return ResponseEntity.status(status).body(null);
        }
        this.articleConfService.delete(id);
        int status = 200;
        return ResponseEntity.status(status).body("Article supprimer avec succes");
    }

}
