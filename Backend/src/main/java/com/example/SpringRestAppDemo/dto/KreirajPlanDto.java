/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Milena
 */
public class KreirajPlanDto implements Serializable{
 
    private Long skolskaGodinaID;
    private List<Long> predmetIDs;
    private boolean kopirajPrethodnu;
 
    public Long getSkolskaGodinaID() {
        return skolskaGodinaID;
    }
 
    public void setSkolskaGodinaID(Long skolskaGodinaID) {
        this.skolskaGodinaID = skolskaGodinaID;
    }
 
    public List<Long> getPredmetIDs() {
        return predmetIDs;
    }
 
    public void setPredmetIDs(List<Long> predmetIDs) {
        this.predmetIDs = predmetIDs;
    }
 
    public boolean isKopirajPrethodnu() {
        return kopirajPrethodnu;
    }
 
    public void setKopirajPrethodnu(boolean kopirajPrethodnu) {
        this.kopirajPrethodnu = kopirajPrethodnu;
    }
 
}
