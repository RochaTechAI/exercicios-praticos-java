package com.uanderson.exercicios.streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnalisadorVendasTest {

    private AnalisadorVendas analisador;
    private List<Venda> vendasExemplo;

    @BeforeEach
    void setUp() {
        analisador = new AnalisadorVendas();
        vendasExemplo = List.of(
                new Venda(1L, "Ana", 1500.0, CategoriaEnum.ELETRONICOS),
                new Venda(2L, "Bruno", 200.0, CategoriaEnum.VESTUARIO),
                new Venda(3L, "Carla", 50.0, CategoriaEnum.ALIMENTACAO),
                new Venda(4L, "Daniel", 3000.0, CategoriaEnum.ELETRONICOS)
        );
    }

    @Test
    @DisplayName("Deve filtrar apenas as vendas da categoria ELETRONICOS")
    void deveFiltrarPorCategoria() {
        List<Venda> eletronicos = analisador.filtrarPorCategoria(vendasExemplo, CategoriaEnum.ELETRONICOS);

        assertEquals(2, eletronicos.size());
        assertTrue(eletronicos.stream().allMatch(v -> v.categoria() == CategoriaEnum.ELETRONICOS));
    }

    @Test
    @DisplayName("Deve somar corretamente o faturamento total das vendas")
    void deveCalcularFaturamentoTotal() {
        double total = analisador.calcularFaturamentoTotal(vendasExemplo);

        assertEquals(4750.0, total, 0.001); // 1500 + 200 + 50 + 3000
    }

    @Test
    @DisplayName("Deve agrupar as vendas por categoria no Mapa")
    void deveAgruparPorCategoria() {
        Map<CategoriaEnum, List<Venda>> agrupado = analisador.agruparPorCategoria(vendasExemplo);

        assertEquals(2, agrupado.get(CategoriaEnum.ELETRONICOS).size());
        assertEquals(1, agrupado.get(CategoriaEnum.VESTUARIO).size());
        assertEquals(1, agrupado.get(CategoriaEnum.ALIMENTACAO).size());
    }

    @Test
    @DisplayName("Deve encontrar a venda de maior valor com sucesso")
    void deveBuscarMaiorVenda() {
        Optional<Venda> maiorVenda = analisador.buscarMaiorVenda(vendasExemplo);

        assertTrue(maiorVenda.isPresent());
        assertEquals(3000.0, maiorVenda.get().valor());
        assertEquals("Daniel", maiorVenda.get().cliente());
    }
}