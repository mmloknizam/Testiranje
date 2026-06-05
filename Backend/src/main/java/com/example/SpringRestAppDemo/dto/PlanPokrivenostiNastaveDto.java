/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Marija
 */
public class PlanPokrivenostiNastaveDto implements Serializable{
    private Long predmetID;
    private String nazivPredmeta;
    private String predavanja;           
    private String vezbe;                
    private String laboratorijskeVezbe;
    private String godina;
    private int brojSatiNastave;
    private List<Long> pokrivenostNastaveIDs;

    public PlanPokrivenostiNastaveDto() {
    }

    public PlanPokrivenostiNastaveDto(Long predmetID, String nazivPredmeta, String predavanja, String vezbe, String laboratorijskeVezbe, String godina, int brojSatiNastave, List<Long> pokrivenostNastaveIDs) {
        this.predmetID = predmetID;
        this.nazivPredmeta = nazivPredmeta;
        this.predavanja = predavanja;
        this.vezbe = vezbe;
        this.laboratorijskeVezbe = laboratorijskeVezbe;
        this.godina = godina;
        this.brojSatiNastave = brojSatiNastave;
        this.pokrivenostNastaveIDs = pokrivenostNastaveIDs;
    }

    public Long getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(Long predmetID) {
        this.predmetID = predmetID;
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getPredavanja() {
        return predavanja;
    }

    public void setPredavanja(String predavanja) {
        this.predavanja = predavanja;
    }

    public String getVezbe() {
        return vezbe;
    }

    public void setVezbe(String vezbe) {
        this.vezbe = vezbe;
    }

    public String getLaboratorijskeVezbe() {
        return laboratorijskeVezbe;
    }

    public void setLaboratorijskeVezbe(String laboratorijskeVezbe) {
        this.laboratorijskeVezbe = laboratorijskeVezbe;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public int getBrojSatiNastave() {
        return brojSatiNastave;
    }

    public void setBrojSatiNastave(int brojSatiNastave) {
        this.brojSatiNastave = brojSatiNastave;
    }

    public List<Long> getPokrivenostNastaveIDs() {
        return pokrivenostNastaveIDs;
    }

    public void setPokrivenostNastaveIDs(List<Long> pokrivenostNastaveIDs) {
        this.pokrivenostNastaveIDs = pokrivenostNastaveIDs;
    }
}
