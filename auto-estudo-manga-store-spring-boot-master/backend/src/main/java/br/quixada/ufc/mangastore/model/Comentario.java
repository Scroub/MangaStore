package br.quixada.ufc.mangastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private int rating;
    @ManyToOne
    @JsonBackReference
    private Manga manga;
}
