/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "nastavnik")
public class Nastavnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nastavnikID")
    private Long nastavnikID;
    @Column(name = "ime")
    private String ime;
    @Column(name = "prezime")
    private String prezime;
    @ManyToOne
    @JoinColumn(name = "zvanjeID")
    private Zvanje zvanje;
    @OneToOne
    @JoinColumn(name = "korisnickiProfilID")
    @JsonIgnore
    private KorisnickiProfil korisnickiProfil;

    public Nastavnik() {
    }

    public Nastavnik(Long nastavnikID, String ime, String prezime, Zvanje zvanje, KorisnickiProfil korisnickiProfil) {
        this.nastavnikID = nastavnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanje = zvanje;
        this.korisnickiProfil = korisnickiProfil;
    }

    @Override
    public String toString() {
        return ime+ " " + prezime;
    }

    public Long getNastavnikID() {
        return nastavnikID;
    }

    public void setNastavnikID(Long nastavnikID) {
        this.nastavnikID = nastavnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Zvanje getZvanje() {
        return zvanje;
    }

    public void setZvanje(Zvanje zvanje) {
        this.zvanje = zvanje;
    }

    public KorisnickiProfil getKorisnickiProfil() {
        return korisnickiProfil;
    }

    public void setKorisnickiProfil(KorisnickiProfil korisnickiProfil) {
        this.korisnickiProfil = korisnickiProfil;
    }
    
    
    
}
