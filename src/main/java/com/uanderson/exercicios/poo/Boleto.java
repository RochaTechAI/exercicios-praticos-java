package com.uanderson.exercicios.poo;

public record Boleto(String codigoBarras) implements MetodoPagamento {

    @Override
    public double calcularValorTotal(double valorOriginal) {
        double taxaFixa = 2.00; // Taxa fixa de R$ 2,00
        return valorOriginal + taxaFixa;
    }
}