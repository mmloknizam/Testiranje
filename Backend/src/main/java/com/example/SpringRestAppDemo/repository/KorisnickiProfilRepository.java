/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.repository;

import com.example.SpringRestAppDemo.entity.KorisnickiProfil;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Milena
 */
@Repository
public interface KorisnickiProfilRepository extends JpaRepository<KorisnickiProfil, Long>{
    public Optional<KorisnickiProfil> findByEmail(String email);
    public Optional<KorisnickiProfil> findByNastavnik_NastavnikID(Long nastavnikID);
}
