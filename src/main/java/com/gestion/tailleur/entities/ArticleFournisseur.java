package com.gestion.tailleur.entities;

import jakarta.persistence.*;

import lombok.Getter;

import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "article_fournisseur")
public class ArticleFournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @ManyToOne
    @JoinColumn(name = "id_article")
    private ArticleConf articleConf;
    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private Fournisseur fournisseur;
}
