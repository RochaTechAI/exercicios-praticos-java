package com.uanderson.exercicios.streams;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnalisadorVendas {

    /**
     * Filtra vendas por uma determinada categoria.
     */
    public List<Venda> filtrarPorCategoria(List<Venda> vendas, CategoriaEnum categoria) {
        if (vendas == null || categoria == null) {
            return List.of();
        }

        return vendas.stream()
                .filter(v -> v.categoria() == categoria)
                .toList(); // Recurso do Java 16+ para coletar em lista imutável
    }

    /**
     * Calcula a soma total do valor de todas as vendas.
     */
    public double calcularFaturamentoTotal(List<Venda> vendas) {
        if (vendas == null || vendas.isEmpty()) {
            return 0.0;
        }

        return vendas.stream()
                .mapToDouble(Venda::valor)
                .sum();
    }

    /**
     * Agrupa as vendas pela sua categoria.
     */
    public Map<CategoriaEnum, List<Venda>> agruparPorCategoria(List<Venda> vendas) {
        if (vendas == null) {
            return Map.of();
        }

        return vendas.stream()
                .collect(Collectors.groupingBy(Venda::categoria));
    }

    /**
     * Retorna a venda com o maior valor financeiro.
     */
    public Optional<Venda> buscarMaiorVenda(List<Venda> vendas) {
        if (vendas == null || vendas.isEmpty()) {
            return Optional.empty();
        }

        return vendas.stream()
                .max((v1, v2) -> Double.compare(v1.valor(), v2.valor()));
    }
}