/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;


import java.io.Serializable;

/**
 *
 * @author Marija
 */
public class DodajPredmetDto implements Serializable{
    private String naziv;
    private Integer brojEspb;
    private Integer fondPredavanja;
    private Integer fondVezbi;
    private Integer fondLabVezbi;

    public DodajPredmetDto() {
    }

    public DodajPredmetDto(String naziv, Integer brojEspb, Integer fondPredavanja, Integer fondVezbi, Integer fondLabVezbi) {
        this.naziv = naziv;
        this.brojEspb = brojEspb;
        this.fondPredavanja = fondPredavanja;
        this.fondVezbi = fondVezbi;
        this.fondLabVezbi = fondLabVezbi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojEspb() {
        return brojEspb;
    }

    public void setBrojEspb(Integer brojEspb) {
        this.brojEspb = brojEspb;
    }

    public Integer getFondPredavanja() {
        return fondPredavanja;
    }

    public void setFondPredavanja(Integer fondPredavanja) {
        this.fondPredavanja = fondPredavanja;
    }

    public Integer getFondVezbi() {
        return fondVezbi;
    }

    public void setFondVezbi(Integer fondVezbi) {
        this.fondVezbi = fondVezbi;
    }

    public Integer getFondLabVezbi() {
        return fondLabVezbi;
    }

    public void setFondLabVezbi(Integer fondLabVezbi) {
        this.fondLabVezbi = fondLabVezbi;
    }
    
}
