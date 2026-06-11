package com.example.Selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();

        // stabilniji run (preporuka za testove)
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void successfulLoginTest() {

        String email = "dusan.savic@fon.bg.ac.rs";
        String password = "Dusan88!";

        driver.get("http://localhost:3000/login");

        // EMAIL
        driver.findElement(By.cssSelector("input[type='text']"))
                .sendKeys(email);

        // PASSWORD
        driver.findElement(By.cssSelector("input[type='password']"))
                .sendKeys(password);

        // LOGIN CLICK
        driver.findElement(By.xpath("//button[text()='Login']"))
                .click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        Boolean tokenExists = wait.until(d -> {
            JavascriptExecutor js = (JavascriptExecutor) d;

            String token = (String) js.executeScript(
                    "return window.localStorage.getItem('token');"
            );

            return token != null && !token.isEmpty();
        });

        assertTrue(tokenExists, "Login nije uspeo - token nije u localStorage");
    }
}