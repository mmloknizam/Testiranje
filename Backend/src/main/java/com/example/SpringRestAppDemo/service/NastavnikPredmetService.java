/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.service;

import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import com.example.SpringRestAppDemo.entity.Predmet;
import java.util.List;

/**
 *
 * @author Marija
 */
public interface NastavnikPredmetService {

    NastavnikPredmet dodaj(Long nastavnikID, Long predmetID);
    List<Predmet> predmetiNastavnika(Long nastavnikID);
    List<Nastavnik> nastavniciPredmeta(Long predmetID);
    void obrisiPredmetZaNastavnika(Long nastavnikID, Long predmetID);
    void obrisiNastavnika(Long nastavnikID);
    void obrisiPredmet(Long predmetID);
    void obrisiNastavnikaZaPredmet(Long nastavnikID, Long predmetID);
}
