package br.quixada.ufc.mangastore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String provider;
    private String password;
    private String resetPasswordToken;
    private String confirmationToken;
    private boolean confirmed;
    private boolean blocked;

}
