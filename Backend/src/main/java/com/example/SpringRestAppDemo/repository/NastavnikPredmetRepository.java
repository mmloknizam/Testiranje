/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.repository;

import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marija
 */
@Repository
public interface NastavnikPredmetRepository extends JpaRepository<NastavnikPredmet, Long>{
    
    List<NastavnikPredmet> findByPredmet_PredmetID(Long predmetID);
    List<NastavnikPredmet> findByNastavnik_NastavnikID(Long nastavnikID);
    Optional<NastavnikPredmet> findByNastavnik_NastavnikIDAndPredmet_PredmetID(Long nastavnikID, Long predmetID);

    public void deleteAllByNastavnik_NastavnikID(Long nastavnikID);

    public void deleteAllByPredmet_PredmetID(Long predmetID);

}
