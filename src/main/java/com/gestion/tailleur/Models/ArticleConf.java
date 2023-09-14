package com.gestion.tailleur.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.Where;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "article_conf")
//@Where(clause = "")
//@SQLInsert()
@SQLDelete(sql = "update article_conf c c.")
public class ArticleConf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("libelle")
    private String libelle;
    @JsonProperty("prix")
    private int prix;
    @JsonProperty("stock")
    private int stock;
    @JsonProperty("image")
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;

    @JsonProperty("reference")
    private String reference;

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    @JsonProperty("categories")
    private Categories categorie;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_fournisseur",
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_fournisseur")
    )
    @JsonProperty("fournisseurs")
    private Set<Fournisseur> fournisseurs;


}
