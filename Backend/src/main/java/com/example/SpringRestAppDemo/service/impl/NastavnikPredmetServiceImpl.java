/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.service.impl;


import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import com.example.SpringRestAppDemo.entity.Predmet;
import com.example.SpringRestAppDemo.repository.NastavnikPredmetRepository;
import com.example.SpringRestAppDemo.repository.NastavnikRepository;
import com.example.SpringRestAppDemo.repository.PredmetRepository;
import com.example.SpringRestAppDemo.service.NastavnikPredmetService;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NastavnikPredmetServiceImpl implements NastavnikPredmetService {

    private final NastavnikPredmetRepository npRepository;
    private final NastavnikRepository nastavnikRepository;
    private final PredmetRepository predmetRepository;

    public NastavnikPredmetServiceImpl(NastavnikPredmetRepository npRepository,
                                       NastavnikRepository nastavnikRepository,
                                       PredmetRepository predmetRepository) {
        this.npRepository = npRepository;
        this.nastavnikRepository = nastavnikRepository;
        this.predmetRepository = predmetRepository;
    }

    @Override
    public List<Predmet> predmetiNastavnika(Long nastavnikID) {
        List<NastavnikPredmet> lista = npRepository.findByNastavnik_NastavnikID(nastavnikID);
        List<Predmet> predmeti = new ArrayList<>();
        for (NastavnikPredmet np : lista) {
            predmeti.add(np.getPredmet());
        }
        return predmeti;
    }

    @Override
    public List<Nastavnik> nastavniciPredmeta(Long predmetID) {
        List<NastavnikPredmet> lista = npRepository.findByPredmet_PredmetID(predmetID);
        List<Nastavnik> nastavnici = new ArrayList<>();
        for (NastavnikPredmet np : lista) {
            nastavnici.add(np.getNastavnik());
        }
        return nastavnici;
    }

    @Override
    public NastavnikPredmet dodaj(Long nastavnikID, Long predmetID) {
        Nastavnik nastavnik = nastavnikRepository.findById(nastavnikID)
                .orElseThrow(() -> new RuntimeException("Nastavnik nije pronađen!"));

        Predmet predmet = predmetRepository.findById(predmetID)
                .orElseThrow(() -> new RuntimeException("Predmet nije pronađen!"));

        Optional<NastavnikPredmet> npCheck = npRepository.findByNastavnik_NastavnikIDAndPredmet_PredmetID(nastavnikID, predmetID);

        if (npCheck.isPresent()) {
            throw new RuntimeException("Veza između nastavnika i predmeta već postoji!");
        }

        NastavnikPredmet np = new NastavnikPredmet(predmet, nastavnik);
        return npRepository.save(np);
    }
    
    @Override
    public void obrisiPredmetZaNastavnika(Long nastavnikID, Long predmetID) {
        NastavnikPredmet np = npRepository
            .findByNastavnik_NastavnikIDAndPredmet_PredmetID(nastavnikID, predmetID)
            .orElseThrow(() -> new RuntimeException("Veza ne postoji!"));

        npRepository.delete(np);
    }

    @Override
    public void obrisiNastavnika(Long nastavnikID) {
        Nastavnik nastavnik = nastavnikRepository.findById(nastavnikID)
            .orElseThrow(() -> new RuntimeException("Nastavnik ne postoji!"));

        npRepository.deleteAllByNastavnik_NastavnikID(nastavnikID);

        nastavnikRepository.delete(nastavnik);
    }

    @Override
    public void obrisiPredmet(Long predmetID) {
        Predmet predmet = predmetRepository.findById(predmetID)
                .orElseThrow(() -> new RuntimeException("Predmet ne postoji!"));

        npRepository.deleteAllByPredmet_PredmetID(predmetID);

        predmetRepository.delete(predmet);
    }

    @Override
    public void obrisiNastavnikaZaPredmet(Long nastavnikID, Long predmetID) {
        NastavnikPredmet np = npRepository
                .findByNastavnik_NastavnikIDAndPredmet_PredmetID(nastavnikID, predmetID)
                .orElseThrow(() -> new RuntimeException("Veza ne postoji!"));

        npRepository.delete(np);
    }
}



