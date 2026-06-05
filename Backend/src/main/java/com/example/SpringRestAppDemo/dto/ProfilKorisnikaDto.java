/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;


import java.io.Serializable;

/**
 *
 * @author Milena
 */
public class ProfilKorisnikaDto implements Serializable{
    private Long korisnickiProfilID;
    private String email;
    private Long ulogaID;
    private String ulogaTip;
    private Long nastavnikID;
    private String ime;
    private String prezime;
    private Long zvanjeID;
    private String zvanjeNaziv;

    public ProfilKorisnikaDto() {
    }

    public ProfilKorisnikaDto(Long korisnickiProfilID, String email, Long ulogaID, String ulogaTip, Long nastavnikID, String ime, String prezime, Long zvanjeID, String zvanjeNaziv) {
        this.korisnickiProfilID = korisnickiProfilID;
        this.email = email;
        this.ulogaID = ulogaID;
        this.ulogaTip = ulogaTip;
        this.nastavnikID = nastavnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.zvanjeID = zvanjeID;
        this.zvanjeNaziv = zvanjeNaziv;
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

    public Long getUlogaID() {
        return ulogaID;
    }

    public void setUlogaID(Long ulogaID) {
        this.ulogaID = ulogaID;
    }

    public String getUlogaTip() {
        return ulogaTip;
    }

    public void setUlogaTip(String ulogaTip) {
        this.ulogaTip = ulogaTip;
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

    public Long getZvanjeID() {
        return zvanjeID;
    }

    public void setZvanjeID(Long zvanjeID) {
        this.zvanjeID = zvanjeID;
    }

    public String getZvanjeNaziv() {
        return zvanjeNaziv;
    }

    public void setZvanjeNaziv(String zvanjeNaziv) {
        this.zvanjeNaziv = zvanjeNaziv;
    } 
    
}
