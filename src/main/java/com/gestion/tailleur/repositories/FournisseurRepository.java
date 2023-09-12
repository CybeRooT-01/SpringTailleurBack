package com.gestion.tailleur.repositories;

import com.gestion.tailleur.Models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
}
