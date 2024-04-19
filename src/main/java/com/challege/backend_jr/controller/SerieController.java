package com.challege.backend_jr.controller;

import com.challege.backend_jr.entity.Serie;
import com.challege.backend_jr.service.SerieService;
import com.challege.backend_jr.service.SerieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {


    @Autowired
    private SerieService serieService;


    @PostMapping
    public ResponseEntity<Serie> createSerie(@RequestBody @Valid Serie serie) {
        Serie newSerie = serieService.createSerie(serie);
        return new ResponseEntity(newSerie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> updateSerie (@PathVariable Long id, @RequestBody Serie serie) {
        Serie updateSerie = serieService.updateserie(id, serie);
        return ResponseEntity.ok().body(updateSerie);
    }

    @GetMapping
    public ResponseEntity<List<Serie>> ListSerie() {
        List<Serie> serieList = serieService.getAllSerie();
        return ResponseEntity.ok().body(serieList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Serie> delete(@PathVariable Long id) {
        serieService.serieDelete(id);
        return ResponseEntity.noContent().build();
    }
}
