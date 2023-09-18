package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
//    Fournisseur findFournisseurByAddress(String adress);

}
