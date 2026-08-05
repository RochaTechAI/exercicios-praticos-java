package com.uanderson.exercicios.concorrencia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ProcessadorPedidoAssincronoTest {

    private ProcessadorPedidoAssincrono processador;

    @BeforeEach
    void setUp() {
        processador = new ProcessadorPedidoAssincrono();
    }

    @Test
    @DisplayName("Deve processar pedido com sucesso quando houver estoque")
    void deveProcessarPedidoComSucesso() throws ExecutionException, InterruptedException {
        Pedido pedido = new Pedido("PED-123", 150.0, true);

        CompletableFuture<String> resultadoFuture = processador.executarFluxoCompleto(pedido);

        // .get() aguarda a conclusão da Thread assíncrona para validar no teste
        String resultado = resultadoFuture.get();

        assertEquals("PAGAMENTO_APROVADO_PED-123", resultado);
    }

    @Test
    @DisplayName("Deve capturar e tratar falha assíncrona quando não houver estoque")
    void deveTratarFalhaQuandoSemEstoque() throws ExecutionException, InterruptedException {
        Pedido pedido = new Pedido("PED-999", 80.0, false);

        CompletableFuture<String> resultadoFuture = processador.executarFluxoCompleto(pedido);
        String resultado = resultadoFuture.get();

        assertTrue(resultado.startsWith("FALHA_PROCESSAMENTO"));
        assertTrue(resultado.contains("Estoque indisponível"));
    }
}