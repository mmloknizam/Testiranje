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
public class DodajNastavnikaDto implements Serializable{
    private String ime;
    private String prezime;
    private Long zvanjeID;

    public DodajNastavnikaDto() {
    }

    public DodajNastavnikaDto(String ime, String prezime, Long zvanjeID) {
        this.ime = ime;
        this.prezime = prezime;
        this.zvanjeID = zvanjeID;
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
}
