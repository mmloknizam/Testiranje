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
public class NastavnikPredmetDto implements Serializable{
    private Long nastavnikID;
    private Long predmetID;

    public Long getNastavnikID() {
        return nastavnikID;
    }

    public void setNastavnikID(Long nastavnikID) {
        this.nastavnikID = nastavnikID;
    }

    public Long getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(Long predmetID) {
        this.predmetID = predmetID;
    }

    
}
