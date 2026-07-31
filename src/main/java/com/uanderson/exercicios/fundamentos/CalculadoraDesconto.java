package com.uanderson.exercicios.fundamentos;

public class CalculadoraDesconto {

    /**
     * Calcula o valor final da compra aplicando descontos progressivos e bônus para clientes VIP.
     *
     * @param valorOriginal Valor bruto da compra
     * @param isVip Se o cliente possui status VIP
     * @return Valor final ajustado com os descontos
     * @throws IllegalArgumentException Se o valor for menor ou igual a zero
     */
    public double calcularValorFinal(double valorOriginal, boolean isVip) {
        if (valorOriginal <= 0) {
            throw new IllegalArgumentException("O valor da compra deve ser maior que zero.");
        }

        double percentualDesconto = 0.0;

        // Regra de desconto por faixa de valor
        if (valorOriginal >= 500.0) {
            percentualDesconto = 0.10; // 10%
        } else if (valorOriginal >= 100.0) {
            percentualDesconto = 0.05; // 5%
        }

        // Bônus adicional para cliente VIP (+5%)
        if (isVip) {
            percentualDesconto += 0.05;
        }

        double valorDesconto = valorOriginal * percentualDesconto;
        return valorOriginal - valorDesconto;
    }
}