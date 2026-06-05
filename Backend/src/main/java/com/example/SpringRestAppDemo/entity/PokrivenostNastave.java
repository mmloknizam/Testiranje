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
@Table(name = "pokrivenostnastave")
public class PokrivenostNastave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokrivenostNastaveID")
    private Long pokrivenostNastaveID;
    @Column(name = "brojSatiNastave")
    private int brojSatiNastave;
    @ManyToOne
    @JoinColumn(name = "predmetID")
    private Predmet predmet;
    @ManyToOne
    @JoinColumn(name = "nastavnikID")
    private Nastavnik nastavnik;
    @ManyToOne
    @JoinColumn(name = "oblikNastaveID")
    private OblikNastave oblikNastave;
    @ManyToOne
    @JoinColumn(name = "skolskaGodinaID")
    private SkolskaGodina skolskaGodina;

    public PokrivenostNastave() {
    }

    public PokrivenostNastave(Long pokrivenostNastaveID, int brojSatiNastave, Predmet predmet, Nastavnik nastavnik, OblikNastave oblikNastave, SkolskaGodina skolskaGodina) {
        this.pokrivenostNastaveID = pokrivenostNastaveID;
        this.brojSatiNastave = brojSatiNastave;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
        this.oblikNastave = oblikNastave;
        this.skolskaGodina = skolskaGodina;
    }

    public Long getPokrivenostNastaveID() {
        return pokrivenostNastaveID;
    }

    public void setPokrivenostNastaveID(Long pokrivenostNastaveID) {
        this.pokrivenostNastaveID = pokrivenostNastaveID;
    }

    public int getBrojSatiNastave() {
        return brojSatiNastave;
    }

    public void setBrojSatiNastave(int brojSatiNastave) {
        this.brojSatiNastave = brojSatiNastave;
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

    public OblikNastave getOblikNastave() {
        return oblikNastave;
    }

    public void setOblikNastave(OblikNastave oblikNastave) {
        this.oblikNastave = oblikNastave;
    }

    public SkolskaGodina getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(SkolskaGodina skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

    @Override
    public String toString() {
        return "PokrivenostNastave{" + "pokrivenostNastaveID=" + pokrivenostNastaveID + ", brojSatiNastave=" + brojSatiNastave + ", predmet=" + predmet + ", nastavnik=" + nastavnik + ", oblikNastave=" + oblikNastave + ", skolskaGodina=" + skolskaGodina + '}';
    }
    
    
}
