/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.service;

import com.example.SpringRestAppDemo.dto.KreirajPlanDto;
import com.example.SpringRestAppDemo.dto.PlanPokrivenostiNastaveDto;
import com.example.SpringRestAppDemo.dto.PokrivenostNastaveDto;
import java.util.List;

/**
 *
 * @author Milena
 */
public interface PokrivenostNastaveService {
    List<PokrivenostNastaveDto> findAll();
    //PokrivenostNastaveDto save(PokrivenostNastaveDto pokrivenostNastaveDto) throws Exception;
    List<PlanPokrivenostiNastaveDto> getPlanZaGodinu(Long skolskaGodinaID);
    void deleteBatch(List<Long> ids);
    List<PokrivenostNastaveDto> findByPredmetAndGodina(Long predmetID, Long skolskaGodinaID);
    void deleteOne(Long id);
    PokrivenostNastaveDto savePlan(PokrivenostNastaveDto dto) throws Exception;
    PokrivenostNastaveDto saveDetalji(PokrivenostNastaveDto dto);
    PokrivenostNastaveDto updateDetalji(Long id,PokrivenostNastaveDto dto);
    public void kreirajPlan(KreirajPlanDto dto);
    void deleteByGodina(Long skolskaGodinaID);
}
