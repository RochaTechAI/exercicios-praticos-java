package com.uanderson.exercicios.poo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorPagamentoTest {

    private ProcessadorPagamento processador;

    @BeforeEach
    void setUp() {
        processador = new ProcessadorPagamento();
    }

    @Test
    @DisplayName("Deve processar pagamento via Pix sem aplicar taxas")
    void deveProcessarPagamentoPixSemTaxa() {
        MetodoPagamento pix = new Pix("uanderson@email.com");
        double valorFinal = processador.processar(pix, 100.0);

        assertEquals(100.0, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Deve aplicar taxa de 2.5% em pagamentos com Cartão de Crédito")
    void deveProcessarPagamentoCartaoComTaxa() {
        MetodoPagamento cartao = new CartaoCredito("1234-5678-9012-3456", 1);
        double valorFinal = processador.processar(cartao, 200.0);

        // 200 + 2.5% (5.0) = 205.0
        assertEquals(205.0, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Deve aplicar taxa fixa de R$ 2,00 em pagamentos com Boleto")
    void deveProcessarPagamentoBoletoComTaxaFixa() {
        MetodoPagamento boleto = new Boleto("34191.79001 01043.510047 91020.150008 5 9000000010000");
        double valorFinal = processador.processar(boleto, 50.0);

        // 50 + 2.0 = 52.0
        assertEquals(52.0, valorFinal, 0.001);
    }

    @Test
    @DisplayName("Deve lançar exceção quando o valor do pagamento for inválido")
    void deveLancarExcecaoParaValorInvalido() {
        MetodoPagamento pix = new Pix("chave-pix");

        assertThrows(
                IllegalArgumentException.class,
                () -> processador.processar(pix, 0.0)
        );
    }
}