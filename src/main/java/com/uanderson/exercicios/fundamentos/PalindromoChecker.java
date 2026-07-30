package com.uanderson.exercicios.fundamentos;

public class PalindromoChecker {

    /**
     * Verifica se uma string é um palíndromo.
     * Exemplo: "arara", "A base do teto desaba"
     */
    public boolean ehPalindromo(String texto) {
        if (texto == null) {
            return false;
        }

        // Remove espaços, acentos/símbolos simples e converte para minúsculas
        String textoLimpo = texto.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        if (textoLimpo.isEmpty()) {
            return false;
        }

        String invertido = new StringBuilder(textoLimpo).reverse().toString();
        return textoLimpo.equals(invertido);
    }
}