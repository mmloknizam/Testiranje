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
public class PromenaLozinkeDto implements Serializable{
    private String staraLozinka;
    private String novaLozinka;
    private String potvrdaNoveLozinke;

    public PromenaLozinkeDto() {
    }

    public PromenaLozinkeDto(String staraLozinka, String novaLozinka, String potvrdaNoveLozinke) {
        this.staraLozinka = staraLozinka;
        this.novaLozinka = novaLozinka;
        this.potvrdaNoveLozinke = potvrdaNoveLozinke;
    }

    public String getStaraLozinka() {
        return staraLozinka;
    }

    public void setStaraLozinka(String staraLozinka) {
        this.staraLozinka = staraLozinka;
    }

    public String getNovaLozinka() {
        return novaLozinka;
    }

    public void setNovaLozinka(String novaLozinka) {
        this.novaLozinka = novaLozinka;
    }

    public String getPotvrdaNoveLozinke() {
        return potvrdaNoveLozinke;
    }

    public void setPotvrdaNoveLozinke(String potvrdaNoveLozinke) {
        this.potvrdaNoveLozinke = potvrdaNoveLozinke;
    }
    
}
