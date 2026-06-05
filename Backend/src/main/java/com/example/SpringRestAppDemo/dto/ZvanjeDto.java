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
public class ZvanjeDto implements Serializable{
    private Long zvanjeID;
    private String naziv;

    public ZvanjeDto() {
    }

    public ZvanjeDto(Long zvanjeID, String naziv) {
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
    
    
}
