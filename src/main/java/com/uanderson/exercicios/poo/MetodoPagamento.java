package com.uanderson.exercicios.poo;

/**
 * Sealed Interface que restringe os métodos de pagamento permitidos no sistema.
 * Apenas os Records autorizados na cláusula 'permits' podem implementá-la.
 */
public sealed interface MetodoPagamento permits CartaoCredito, Pix, Boleto {
    double calcularValorTotal(double valorOriginal);
}