/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.SpringRestAppDemo.repository;

import com.example.SpringRestAppDemo.entity.Verifikacija;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Marija
 */
@Repository
public interface VerifikacijaRepository extends JpaRepository<Verifikacija, Long>{

    public Optional<Verifikacija> findByEmailAndKod(String email, String kod);

    public Optional<Verifikacija> findByEmail(String email);
    
}
