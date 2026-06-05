/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.dto;

import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.OblikNastave;
import com.example.SpringRestAppDemo.entity.Predmet;
import com.example.SpringRestAppDemo.entity.SkolskaGodina;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 *
 * @author Milena
 */
public class PokrivenostNastaveDto implements Serializable{
    private Long pokrivenostNastaveID;
    @Min(value = 0, message = "Broj sati nastave ne može biti negativan")
    private int brojSatiNastave;
    private Predmet predmet;
    private Nastavnik nastavnik;
    private OblikNastave oblikNastave;
    private SkolskaGodina skolskaGodina;

    public PokrivenostNastaveDto() {
    }

    public PokrivenostNastaveDto(Long pokrivenostNastaveID, int brojSatiNastave, Predmet predmet, Nastavnik nastavnik, OblikNastave oblikNastave, SkolskaGodina skolskaGodina) {
        this.pokrivenostNastaveID = pokrivenostNastaveID;
        this.brojSatiNastave = brojSatiNastave;
        this.predmet = predmet;
        this.nastavnik = nastavnik;
        this.oblikNastave = oblikNastave;
        this.skolskaGodina = skolskaGodina;
    }

    public Long getPokrivenostNastaveID() {
        return pokrivenostNastaveID;
    }

    public void setPokrivenostNastaveID(Long pokrivenostNastaveID) {
        this.pokrivenostNastaveID = pokrivenostNastaveID;
    }

    public int getBrojSatiNastave() {
        return brojSatiNastave;
    }

    public void setBrojSatiNastave(int brojSatiNastave) {
        this.brojSatiNastave = brojSatiNastave;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Nastavnik getNastavnik() {
        return nastavnik;
    }

    public void setNastavnik(Nastavnik nastavnik) {
        this.nastavnik = nastavnik;
    }

    public OblikNastave getOblikNastave() {
        return oblikNastave;
    }

    public void setOblikNastave(OblikNastave oblikNastave) {
        this.oblikNastave = oblikNastave;
    }

    public SkolskaGodina getSkolskaGodina() {
        return skolskaGodina;
    }

    public void setSkolskaGodina(SkolskaGodina skolskaGodina) {
        this.skolskaGodina = skolskaGodina;
    }

}
