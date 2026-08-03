package com.uanderson.exercicios.poo;

public record CartaoCredito(String numeroCartao, int parcelas) implements MetodoPagamento {

    @Override
    public double calcularValorTotal(double valorOriginal) {
        double taxa = 0.025; // Taxa de 2.5%
        return valorOriginal + (valorOriginal * taxa);
    }
}