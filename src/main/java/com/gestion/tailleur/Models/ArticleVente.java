package com.gestion.tailleur.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArticleVente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonProperty("libelle")
    private String libelle;
    @JsonProperty("marge")
    private int marge;
    @JsonProperty("prix_vente")
    private int prix_vente;
    @JsonProperty("stock")
    private String reference;
    @JsonProperty("promo")
    private int promo;
    @JsonProperty("cout_fabrication")
    private int cout_fabrication;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String image;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    @JsonProperty("categories")
    private Categories categorie;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "vente_conf",
            joinColumns = @JoinColumn(name = "article_vente_id"),
            inverseJoinColumns = @JoinColumn(name = "article_conf_id")
    )
    private Collection<ArticleConf> articleConfs = new ArrayList<>();

//    @Transient
//    private Set<Integer> articleConfIds;
}



















