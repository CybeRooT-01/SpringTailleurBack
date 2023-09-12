package com.gestion.tailleur.controllers;

import com.gestion.tailleur.Models.Fournisseur;
import com.gestion.tailleur.services.FournisseurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FournisseurController {
    private final FournisseurService fournisseurService;

    @GetMapping("/fournisseur")
    public List<Fournisseur> getListeFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.getListeFournisseurs();
        return fournisseurs;
    }


    @GetMapping("/peupler")
    public void peuplerTableFournisseur(){
        this.fournisseurService.peuplerTableFournisseur();
    }
}
