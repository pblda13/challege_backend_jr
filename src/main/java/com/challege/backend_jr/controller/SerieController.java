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
        try {
            Serie newSerie = serieService.createSerie(serie);
            return new ResponseEntity<>(newSerie, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Serie> updateSerie(@PathVariable Long id, @RequestBody Serie serie) {
        try {
            Serie updatedSerie = serieService.updateSerie(id, serie);
            return ResponseEntity.ok().body(updatedSerie);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Serie>> listSeries() {
        List<Serie> serieList = serieService.getAllSeries();
        return ResponseEntity.ok().body(serieList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSerie(@PathVariable Long id) {
        serieService.deleteSerie(id);
        return ResponseEntity.noContent().build();
    }
}
