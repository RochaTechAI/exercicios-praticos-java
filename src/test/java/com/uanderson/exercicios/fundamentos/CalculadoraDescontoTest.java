package com.uanderson.exercicios.fundamentos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraDescontoTest {

    private CalculadoraDesconto calculadora;

    @BeforeEach
    void setUp() {
        calculadora = new CalculadoraDesconto();
    }

    @Test
    @DisplayName("Não deve aplicar desconto para compras menores que R$ 100 de cliente comum")
    void deveCalcularSemDescontoParaClienteComum() {
        double valorFinal = calculadora.calcularValorFinal(80.0, false);
        assertEquals(80.0, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Deve aplicar 5% de desconto para compras entre R$ 100 e R$ 499,99 de cliente comum")
    void deveAplicarCincoPorCentoParaClienteComum() {
        double valorFinal = calculadora.calcularValorFinal(200.0, false);
        assertEquals(190.0, valorFinal, 0.001); // 200 - 5% (10) = 190
    }

    @Test
    @DisplayName("Deve aplicar 10% de desconto para compras de R$ 500 ou mais de cliente comum")
    void deveAplicarDezPorCentoParaClienteComum() {
        double valorFinal = calculadora.calcularValorFinal(1000.0, false);
        assertEquals(900.0, valorFinal, 0.001); // 1000 - 10% (100) = 900
    }

    @Test
    @DisplayName("Deve somar 5% extra de desconto para cliente VIP")
    void deveAplicarDescontoAdicionalParaClienteVip() {
        // R$ 500 para VIP = 10% (faixa) + 5% (VIP) = 15% de desconto
        double valorFinal = calculadora.calcularValorFinal(500.0, true);
        assertEquals(425.0, valorFinal, 0.001); // 500 - 15% (75) = 425
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException para valores de compra inválidos")
    void deveLancarExcecaoParaValoresInvalidos() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculadora.calcularValorFinal(0.0, false)
        );

        assertEquals("O valor da compra deve ser maior que zero.", exception.getMessage());
    }
}
