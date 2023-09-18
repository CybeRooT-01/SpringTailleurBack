package com.gestion.tailleur.Models;

import com.gestion.tailleur.enums.TypeCategories;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.config.Projection;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String libelle;

    private TypeCategories typeCategories;

}
