/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringRestAppDemo.mapper.impl;

import com.example.SpringRestAppDemo.dto.PokrivenostNastaveDto;
import com.example.SpringRestAppDemo.entity.PokrivenostNastave;
import com.example.SpringRestAppDemo.mapper.DtoEntityMapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author Milena
 */
@Component
public class PokrivenostNastaveDtoEntityMapper implements DtoEntityMapper<PokrivenostNastaveDto, PokrivenostNastave> {

    @Override
    public PokrivenostNastaveDto toDto(PokrivenostNastave e) {
        return new PokrivenostNastaveDto(e.getPokrivenostNastaveID(), e.getBrojSatiNastave(), e.getPredmet(), e.getNastavnik(), e.getOblikNastave(), e.getSkolskaGodina());
    }

    @Override
    public PokrivenostNastave toEntity(PokrivenostNastaveDto t) {
        return new PokrivenostNastave(t.getPokrivenostNastaveID(), t.getBrojSatiNastave(), t.getPredmet(), t.getNastavnik(), t.getOblikNastave(), t.getSkolskaGodina());
    }
}
