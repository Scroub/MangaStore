package br.quixada.ufc.mangastore.model;

import jakarta.persistence.*;
import lombok.Data;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @Column(name = "gender")
    private String gender;
    @Column(columnDefinition = "TEXT")
    private String summary;
    @OneToMany(mappedBy = "manga", fetch = FetchType.LAZY)
    @JsonManagedReference
    public List<Comentario> comments;

}
