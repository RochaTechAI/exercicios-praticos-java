package com.uanderson.exercicios.concorrencia;

import java.util.concurrent.CompletableFuture;

public class ProcessadorPedidoAssincrono {

    /**
     * Valida se há estoque suficiente para o pedido de forma assíncrona.
     */
    public CompletableFuture<Pedido> validarEstoque(Pedido pedido) {
        return CompletableFuture.supplyAsync(() -> {
            if (!pedido.estoqueDisponivel()) {
                throw new IllegalStateException("Estoque indisponível para o pedido: " + pedido.id());
            }
            return pedido;
        });
    }

    /**
     * Processa o pagamento de forma assíncrona após a validação do estoque.
     */
    public CompletableFuture<String> processarPagamento(CompletableFuture<Pedido> pedidoFuture) {
        return pedidoFuture.thenApply(pedido -> {
            // Simula o processamento do pagamento
            return "PAGAMENTO_APROVADO_" + pedido.id();
        }).exceptionally(ex -> "PAGAMENTO_RECUSADO: " + ex.getCause().getMessage());
    }

    /**
     * Encadeia todo o fluxo de processamento do pedido de ponta a ponta.
     */
    public CompletableFuture<String> executarFluxoCompleto(Pedido pedido) {
        return validarEstoque(pedido)
                .thenApply(p -> "PAGAMENTO_APROVADO_" + p.id())
                .exceptionally(ex -> "FALHA_PROCESSAMENTO: " + ex.getCause().getMessage());
    }
}