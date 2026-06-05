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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "korisnickiprofil")
public class KorisnickiProfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "korisnickiProfilID")
    private Long korisnickiProfilID;
    @Column(name = "email")
    private String email;
    @Column(name = "lozinka")
    private String lozinka;
    @ManyToOne
    @JoinColumn(name = "ulogaID")
    private Uloga uloga;
    @Column(name = "enabled")
    private boolean enabled = false;
    @OneToOne
    @JoinColumn(name = "nastavnikID", nullable = true)
    private Nastavnik nastavnik;

    public KorisnickiProfil() {
    }

    public KorisnickiProfil(Long korisnickiProfilID, String email, String lozinka, Uloga uloga, Nastavnik nastavnik) {
        this.korisnickiProfilID = korisnickiProfilID;
        this.email = email;
        this.lozinka = lozinka;
        this.uloga = uloga;
        this.nastavnik = nastavnik;
    }
    
    @Override
    public String toString() {
        return email;
    }

    public Long getKorisnickiProfilID() {
        return korisnickiProfilID;
    }

    public void setKorisnickiProfilID(Long korisnickiProfilID) {
        this.korisnickiProfilID = korisnickiProfilID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public Object findByNastavnik_NastavnikID(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
