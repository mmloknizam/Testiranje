/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Marija
 */
@Entity
@Table(name = "zvanje")
public class Zvanje {
    @Id
    @Column(name = "zvanjeID")
    private Long zvanjeID;
    @Column(name = "naziv")
    private String naziv;

    public Zvanje() {
    }

    public Zvanje(Long zvanjeID, String naziv) {
        this.zvanjeID = zvanjeID;
        this.naziv = naziv;
    }

    public Long getZvanjeID() {
        return zvanjeID;
    }

    public void setZvanjeID(Long zvanjeID) {
        this.zvanjeID = zvanjeID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
}
