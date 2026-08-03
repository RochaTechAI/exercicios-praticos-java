package com.uanderson.exercicios.poo;

public record Pix(String chavePix) implements MetodoPagamento {

    @Override
    public double calcularValorTotal(double valorOriginal) {
        return valorOriginal; // Pix não aplica taxas
    }
}