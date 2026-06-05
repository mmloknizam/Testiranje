/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.repository;

import com.example.SpringRestAppDemo.entity.PokrivenostNastave;
import com.example.SpringRestAppDemo.entity.SkolskaGodina;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marija
 */
@Repository
public interface PokrivenostNastaveRepository extends JpaRepository<PokrivenostNastave, Long>{

    public List<PokrivenostNastave> findAllBySkolskaGodina_SkolskaGodinaID(Long skolskaGodinaID);

    public List<PokrivenostNastave> findByPredmet_PredmetIDAndSkolskaGodina_SkolskaGodinaID(Long predmetID,Long skolskaGodinaID);

    @Query("""
        SELECT COALESCE(SUM(p.brojSatiNastave), 0)
        FROM PokrivenostNastave p
        WHERE p.predmet.predmetID = :predmetID
          AND p.skolskaGodina.skolskaGodinaID = :skolskaGodinaID
          AND p.oblikNastave.oblikNastaveID = :oblikNastaveID
    """)
    public int sumSati(@Param("predmetID") Long predmetID,
                @Param("skolskaGodinaID") Long skolskaGodinaID,
                @Param("oblikNastaveID") Long oblikNastaveID);
    
    
    @Query("""
        SELECT DISTINCT p.skolskaGodina
        FROM PokrivenostNastave p
        WHERE p.skolskaGodina.godina < :godina
    """)
    List<SkolskaGodina> findPrethodneGodine(@Param("godina") String godina);

    @Transactional
    @Modifying
    void deleteBySkolskaGodina_SkolskaGodinaID(Long skolskaGodinaID);

    PokrivenostNastave findByPredmet_PredmetIDAndSkolskaGodina_SkolskaGodinaIDAndOblikNastave_OblikNastaveIDAndNastavnik_NastavnikID(
        Long predmetID,
        Long godinaID,
        Long oblikID,
        Long nastavnikID
    );
    
    PokrivenostNastave findByPredmet_PredmetIDAndSkolskaGodina_SkolskaGodinaIDAndOblikNastave_OblikNastaveIDAndNastavnik_NastavnikIDAndPokrivenostNastaveIDNot(
        Long predmetID,
        Long godinaID,
        Long oblikID,
        Long nastavnikID,
        Long id
    );
}
