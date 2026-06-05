/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "nastavnikpredmet")
public class NastavnikPredmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nastavnikPredmetID")
    private Long nastavnikPredmetID;
    @ManyToOne
    @JoinColumn(name = "predmetID")
    private Predmet predmet;
    @ManyToOne
    @JoinColumn(name = "nastavnikID")
    private Nastavnik nastavnik;

    public NastavnikPredmet() {
    }
    
        public NastavnikPredmet(Predmet predmet, Nastavnik nastavnik) {
        this.predmet = predmet;
        this.nastavnik = nastavnik;
    }

    public NastavnikPredmet(Long nastavnikPredmetID, Predmet predmet, Nastavnik nastavnik) {
        this.nastavnikPredmetID = nastavnikPredmetID;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
    }

    public Long getNastavnikPredmetID() {
        return nastavnikPredmetID;
    }

    public void setNastavnikPredmetID(Long nastavnikPredmetID) {
        this.nastavnikPredmetID = nastavnikPredmetID;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }    
    
}
