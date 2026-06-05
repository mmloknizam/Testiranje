/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package com.example.SpringRestAppDemo.controller;
import com.example.SpringRestAppDemo.dto.DodajPredmetDto;
import com.example.SpringRestAppDemo.dto.NastavnikPredmetDto;
import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import com.example.SpringRestAppDemo.entity.Predmet;
import com.example.SpringRestAppDemo.repository.PredmetRepository;
import com.example.SpringRestAppDemo.service.NastavnikPredmetService;
import java.util.List;
import org.springframework.web.bind.annotation.*;
/**
*
* @author Milena
*/
@RestController
@RequestMapping("/api/predmet")
@CrossOrigin(origins = "http://localhost:3000")
public class PredmetController {
 
    private final PredmetRepository predmetRepository;
    private final NastavnikPredmetService npService;

    public PredmetController(PredmetRepository predmetRepository, NastavnikPredmetService npService) {
        this.predmetRepository = predmetRepository;
        this.npService = npService;
    }
 
    @GetMapping
    public List<Predmet> findAll() {
        return predmetRepository.findAll();
    }
    
    @GetMapping("/{predmetID}/nastavnici")
    public List<Nastavnik> getNastavnici(@PathVariable Long predmetID) {
        return npService.nastavniciPredmeta(predmetID);
    }
    
    @PostMapping
    public Predmet dodajPredmet(@RequestBody DodajPredmetDto dto) {        if (dto.getNaziv() == null || dto.getNaziv().isEmpty() ||
            dto.getBrojEspb() == null || dto.getFondPredavanja() == null ||
            dto.getFondVezbi() == null || dto.getFondLabVezbi() == null) {
            throw new RuntimeException("Sva polja su obavezna!");
        }

        Predmet predmet = new Predmet();
        predmet.setNaziv(dto.getNaziv());
        predmet.setBrojEspb(dto.getBrojEspb());
        predmet.setFondPredavanja(dto.getFondPredavanja());
        predmet.setFondVezbi(dto.getFondVezbi());
        predmet.setFondLabVezbi(dto.getFondLabVezbi());

        return predmetRepository.save(predmet);
    }
    
        @PostMapping("/nastavnik")
    public NastavnikPredmet dodajNastavnikaPredmetu(@RequestBody NastavnikPredmetDto dto) {
        return npService.dodaj(dto.getNastavnikID(), dto.getPredmetID());
    }
    
    @DeleteMapping("/{predmetID}")
    public void obrisiPredmet(@PathVariable Long predmetID) {
        npService.obrisiPredmet(predmetID);
    }

    @DeleteMapping("/{predmetID}/nastavnici/{nastavnikID}")
    public void obrisiNastavnikaZaPredmet(@PathVariable Long predmetID,
                                           @PathVariable Long nastavnikID) {
        npService.obrisiNastavnikaZaPredmet(nastavnikID, predmetID);
    }
}