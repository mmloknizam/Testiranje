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
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "predmet")
public class Predmet {
    @Id
    @Column(name = "predmetID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long predmetID;
    @Column(name = "naziv")
    private String naziv;
    @Column(name = "brojEspb")
    private int brojEspb;
    @Column(name = "fondPredavanja")
    private int fondPredavanja;
    @Column(name = "fondVezbi")
    private int fondVezbi;
    @Column(name = "fondLabVezbi")
    private int fondLabVezbi;

    public Predmet() {
    }

    public Predmet(Long predmetID, String naziv, int brojEspb, int fondPredavanja, int fondVezbi, int fondLabVezbi) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.brojEspb = brojEspb;
        this.fondPredavanja = fondPredavanja;
        this.fondVezbi = fondVezbi;
        this.fondLabVezbi = fondLabVezbi;
    }
    
    public Long getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(Long predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojEspb() {
        return brojEspb;
    }

    public void setBrojEspb(int brojEspb) {
        this.brojEspb = brojEspb;
    }

    @Override
    public String toString() {
        return "Predmet{" + "predmetID=" + predmetID + ", naziv=" + naziv + ", brojEspb=" + brojEspb + ", fondPredavanja=" + fondPredavanja + ", fondVezbi=" + fondVezbi + ", fondLabVezbi=" + fondLabVezbi + '}';
    }

    public int getFondPredavanja() {
        return fondPredavanja;
    }

    public void setFondPredavanja(int fondPredavanja) {
        this.fondPredavanja = fondPredavanja;
    }

    public int getFondVezbi() {
        return fondVezbi;
    }

    public void setFondVezbi(int fondVezbi) {
        this.fondVezbi = fondVezbi;
    }

    public int getFondLabVezbi() {
        return fondLabVezbi;
    }

    public void setFondLabVezbi(int fondLabVezbi) {
        this.fondLabVezbi = fondLabVezbi;
    }
}
