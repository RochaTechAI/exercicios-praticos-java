package com.uanderson.exercicios.poo;

public class ProcessadorPagamento {

    /**
     * Processa o pagamento calculando o valor final com base no método fornecido.
     */
    public double processar(MetodoPagamento metodo, double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }
        if (metodo == null) {
            throw new IllegalArgumentException("O método de pagamento não pode ser nulo.");
        }

        return metodo.calcularValorTotal(valor);
    }
}