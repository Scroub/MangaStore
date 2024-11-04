package br.quixada.ufc.mangastore.controller;

import br.quixada.ufc.mangastore.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin("http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuariosRepository repository;

}
