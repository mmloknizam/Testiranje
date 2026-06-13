package com.example.DBUnit;

import com.example.SpringRestAppDemo.SpringRestAppDemoApplication;
import com.example.SpringRestAppDemo.repository.PokrivenostNastaveRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SpringRestAppDemoApplication.class)
public class PokrivenostNastaveRepositoryTest {

    @Autowired
    private PokrivenostNastaveRepository repository;

    @Test
    void testFindAll() {
        var result = repository.findAll();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }
}