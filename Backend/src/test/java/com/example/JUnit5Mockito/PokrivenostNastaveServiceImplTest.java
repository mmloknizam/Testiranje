/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.JUnit5Mockito;

import com.example.SpringRestAppDemo.dto.KreirajPlanDto;
import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import com.example.SpringRestAppDemo.entity.PokrivenostNastave;
import com.example.SpringRestAppDemo.entity.SkolskaGodina;
import com.example.SpringRestAppDemo.repository.NastavnikPredmetRepository;
import com.example.SpringRestAppDemo.repository.PokrivenostNastaveRepository;
import com.example.SpringRestAppDemo.repository.SkolskaGodinaRepository;
import com.example.SpringRestAppDemo.service.impl.PokrivenostNastaveServiceImpl;
import com.example.SpringRestAppDemo.mapper.impl.PokrivenostNastaveDtoEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PokrivenostNastaveServiceImplTest {

    @Mock
    private PokrivenostNastaveRepository pokrivenostNastaveRepository;

    @Mock
    private SkolskaGodinaRepository skolskaGodinaRepository;

    @Mock
    private NastavnikPredmetRepository nastavnikPredmetRepository;

    // ⚠️ BITNO: NE MOCKUJ MAPPER (zbog ByteBuddy problema)
    private PokrivenostNastaveDtoEntityMapper mapper = new PokrivenostNastaveDtoEntityMapper();

    @InjectMocks
    private PokrivenostNastaveServiceImpl service;

    @BeforeEach
    void setUp() {
        // Inject manual mapper jer nije mock
        service = new PokrivenostNastaveServiceImpl(
                pokrivenostNastaveRepository,
                mapper,
                skolskaGodinaRepository,
                nastavnikPredmetRepository
        );
    }

    // -----------------------------
    // 1. PLAN VEĆ POSTOJI (ERROR)
    // -----------------------------
    @Test
    void testKreirajPlan_planAlreadyExists() {

        KreirajPlanDto dto = new KreirajPlanDto();
        dto.setSkolskaGodinaID(1L);
        dto.setKopirajPrethodnu(false);
        dto.setPredmetIDs(List.of(10L));

        when(pokrivenostNastaveRepository
                .findAllBySkolskaGodina_SkolskaGodinaID(1L))
                .thenReturn(List.of(new PokrivenostNastave()));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.kreirajPlan(dto));

        assertEquals("Plan za ovu godinu već postoji!", ex.getMessage());
    }

    // -----------------------------
    // 2. NEMA PREDMETA (ERROR)
    // -----------------------------
    @Test
    void testKreirajPlan_noPredmeti() {

        KreirajPlanDto dto = new KreirajPlanDto();
        dto.setSkolskaGodinaID(1L);
        dto.setKopirajPrethodnu(false);
        dto.setPredmetIDs(List.of());

        when(pokrivenostNastaveRepository
                .findAllBySkolskaGodina_SkolskaGodinaID(1L))
                .thenReturn(List.of());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.kreirajPlan(dto));

        assertEquals("Morate izabrati predmete za kreiranje novog plana!", ex.getMessage());
    }

    // -----------------------------
    // 3. KOPIRANJE - NEMA PRETHODNE GODINE (ERROR)
    // -----------------------------
    @Test
    void testKreirajPlan_copyNoPreviousYear() {

        KreirajPlanDto dto = new KreirajPlanDto();
        dto.setSkolskaGodinaID(2L);
        dto.setKopirajPrethodnu(true);

        when(pokrivenostNastaveRepository
                .findAllBySkolskaGodina_SkolskaGodinaID(2L))
                .thenReturn(List.of());

        SkolskaGodina trenutna = new SkolskaGodina();
        trenutna.setGodina("2025/2026");

        when(skolskaGodinaRepository.findById(2L))
                .thenReturn(java.util.Optional.of(trenutna));

        when(pokrivenostNastaveRepository.findPrethodneGodine("2025/2026"))
                .thenReturn(List.of());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.kreirajPlan(dto));

        assertEquals("Ne postoji prethodna godina za kopiranje!", ex.getMessage());
    }

    // -----------------------------
    // 4. KOPIRANJE - SVE OK (SMOKE TEST)
    // -----------------------------
    @Test
    void testKreirajPlan_copySuccess() {

        KreirajPlanDto dto = new KreirajPlanDto();
        dto.setSkolskaGodinaID(2L);
        dto.setKopirajPrethodnu(true);

        when(pokrivenostNastaveRepository
                .findAllBySkolskaGodina_SkolskaGodinaID(2L))
                .thenReturn(List.of());

        SkolskaGodina trenutna = new SkolskaGodina();
        trenutna.setGodina("2025/2026");

        SkolskaGodina prethodna = new SkolskaGodina();
        prethodna.setSkolskaGodinaID(1L);

        when(skolskaGodinaRepository.findById(2L))
                .thenReturn(java.util.Optional.of(trenutna));

        when(pokrivenostNastaveRepository.findPrethodneGodine("2025/2026"))
                .thenReturn(List.of(prethodna));

        PokrivenostNastave stari = new PokrivenostNastave();
        stari.setBrojSatiNastave(10);

        when(pokrivenostNastaveRepository
                .findAllBySkolskaGodina_SkolskaGodinaID(1L))
                .thenReturn(List.of(stari));

        when(pokrivenostNastaveRepository.save(any()))
                .thenAnswer(i -> i.getArgument(0));

        assertDoesNotThrow(() -> service.kreirajPlan(dto));

        verify(pokrivenostNastaveRepository, atLeastOnce()).save(any());
    }
}