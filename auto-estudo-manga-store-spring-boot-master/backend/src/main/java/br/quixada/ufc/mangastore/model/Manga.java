package br.quixada.ufc.mangastore.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

@Data
@Entity
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long number;
    private float price;
    private String cover;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @OneToMany(mappedBy = "manga")
    public List<Comentario> comments;

}
