package br.quixada.ufc.mangastore.controller;

import br.quixada.ufc.mangastore.model.Manga;
import br.quixada.ufc.mangastore.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import br.quixada.ufc.mangastore.utils.FileUploadUtil;

import java.io.IOException;

@Controller
@CrossOrigin("http://localhost:3000")
public class MangaController {
    @Autowired
    private MangaRepository repository;


    @PostMapping("/mangas/save")
    public RedirectView saveManga(
            @RequestParam("files") MultipartFile multipartFile,
            @RequestParam("title") String title,
            @RequestParam("number") Integer number,
            @RequestParam("price") float price) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Manga manga = new Manga();
        manga.setTitle(title);
        manga.setPrice(price);
        manga.setNumber(number);
        manga.setCover("/img/one_piece/" +fileName);
        Manga savedManga = repository.save(manga);
        String uploadDir = "src/main/resources/static/img/one_piece/";
        FileUploadUtil.saveFile(uploadDir,fileName, multipartFile);
        return new RedirectView("/mangas", true);
    }

    @PutMapping("/mangas/update/{id}")
    public ResponseEntity updateManga(
            @PathVariable("id") Long id,
            @RequestParam(name = "files",required = false) MultipartFile multipartFile,
            @RequestParam("title") String title,
            @RequestParam("number") Integer number,
            @RequestParam("price") float price) throws IOException{
        Manga manga = new Manga();
        manga.setId(id);
        manga.setTitle(title);
        manga.setPrice(price);
        manga.setNumber(number);
        if(multipartFile==null){
            Manga mangaTemp = repository.findById(id).get();
            manga.setCover(mangaTemp.getCover());
        }else{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            manga.setCover("/img//one_piece/" +fileName);
            String uploadDir = "src/main/resources/static/img/one_piece/";
            FileUploadUtil.saveFile(uploadDir,fileName, multipartFile);
        }
        repository.save(manga);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }




}
