package com.uanderson.exercicios.fundamentos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromoCheckerTest {

    private PalindromoChecker checker;

    @BeforeEach
    void setUp() {
        checker = new PalindromoChecker();
    }

    @Test
    @DisplayName("Deve retornar true para palavras palíndromas simples")
    void deveValidarPalindromoSimples() {
        assertTrue(checker.ehPalindromo("arara"));
        assertTrue(checker.ehPalindromo("ovo"));
        assertTrue(checker.ehPalindromo("radar"));
    }

    @Test
    @DisplayName("Deve ignorar maiúsculas e espaços em frases palíndromas")
    void deveValidarFrasesPalindromasComEspacosEMaiusculas() {
        assertTrue(checker.ehPalindromo("A base do teto desaba"));
        assertTrue(checker.ehPalindromo("AaraA"));
    }

    @Test
    @DisplayName("Deve retornar false para palavras que não são palíndromas")
    void deveRetornarFalsoParaTextoNaoPalindromo() {
        assertFalse(checker.ehPalindromo("java"));
        assertFalse(checker.ehPalindromo("desenvolvimento"));
    }

    @Test
    @DisplayName("Deve tratar entradas nulas ou vazias adequadamente")
    void deveTratarEntradasInvalidas() {
        assertFalse(checker.ehPalindromo(null));
        assertFalse(checker.ehPalindromo("   "));
    }
}