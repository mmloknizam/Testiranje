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
public class NastavnikDto implements Serializable{
    private Long nastavnikID;
    private String ime;
    private String prezime;
    private ZvanjeDto zvanje;
    private KorisnickiProfilDto korisnickiProfil;

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

    public ZvanjeDto getZvanje() {
        return zvanje;
    }

    public void setZvanje(ZvanjeDto zvanje) {
        this.zvanje = zvanje;
    }

    public KorisnickiProfilDto getKorisnickiProfil() {
        return korisnickiProfil;
    }

    public void setKorisnickiProfil(KorisnickiProfilDto korisnickiProfil) {
        this.korisnickiProfil = korisnickiProfil;
    }

    
}
