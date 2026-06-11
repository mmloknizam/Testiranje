package com.example.JUnit5Mockito;

import com.example.SpringRestAppDemo.entity.Nastavnik;
import com.example.SpringRestAppDemo.entity.Predmet;
import com.example.SpringRestAppDemo.entity.NastavnikPredmet;
import com.example.SpringRestAppDemo.repository.NastavnikPredmetRepository;
import com.example.SpringRestAppDemo.repository.NastavnikRepository;
import com.example.SpringRestAppDemo.repository.PredmetRepository;
import com.example.SpringRestAppDemo.service.impl.NastavnikPredmetServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NastavnikPredmetServiceImplTest {

    @Mock
    private NastavnikPredmetRepository npRepository;

    @Mock
    private NastavnikRepository nastavnikRepository;

    @Mock
    private PredmetRepository predmetRepository;

    @InjectMocks
    private NastavnikPredmetServiceImpl service;

    @Test
    void shouldAddRelationSuccessfully() {

        Long nastavnikID = 1L;
        Long predmetID = 2L;

        Nastavnik nastavnik = new Nastavnik();
        Predmet predmet = new Predmet();
        NastavnikPredmet saved = new NastavnikPredmet(predmet, nastavnik);

        when(nastavnikRepository.findById(nastavnikID))
                .thenReturn(Optional.of(nastavnik));

        when(predmetRepository.findById(predmetID))
                .thenReturn(Optional.of(predmet));

        when(npRepository.findByNastavnik_NastavnikIDAndPredmet_PredmetID(nastavnikID, predmetID))
                .thenReturn(Optional.empty());

        when(npRepository.save(any(NastavnikPredmet.class)))
                .thenReturn(saved);

        NastavnikPredmet result = service.dodaj(nastavnikID, predmetID);

        assertNotNull(result);

        verify(npRepository, times(1)).save(any(NastavnikPredmet.class));
    }
}