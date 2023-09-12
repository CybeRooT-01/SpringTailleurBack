package com.gestion.tailleur.services;

import com.gestion.tailleur.Models.Fournisseur;
import com.gestion.tailleur.repositories.FournisseurRepository;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;


    public void peuplerTableFournisseur() {
        for (int i = 0; i < 10; i++) {
            Faker faker = new Faker();
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setNom(faker.name().fullName());
            fournisseur.setAddress(faker.address().fullAddress());
            this.fournisseurRepository.save(fournisseur);
        }
    }


    public List<Fournisseur> getListeFournisseurs() {
        return fournisseurRepository.findAll();
    }
}
