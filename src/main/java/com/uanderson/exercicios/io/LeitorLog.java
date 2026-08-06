package com.uanderson.exercicios.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeitorLog {

    /**
     * Converte uma linha de texto formatada em um objeto LogEntry.
     * Exemplo de linha: "2026-08-06 14:30:00 [ERROR] Falha de conexão"
     */
    public LogEntry parsearLinha(String linha) {
        if (linha == null || linha.isBlank()) {
            throw new IllegalArgumentException("Linha de log inválida.");
        }

        int primeiroColchete = linha.indexOf('[');
        int segundoColchete = linha.indexOf(']');

        if (primeiroColchete == -1 || segundoColchete == -1 || segundoColchete <= primeiroColchete) {
            throw new IllegalArgumentException("Formato de log incorreto.");
        }

        String timestamp = linha.substring(0, primeiroColchete).trim();
        String nivelStr = linha.substring(primeiroColchete + 1, segundoColchete).trim();
        String mensagem = linha.substring(segundoColchete + 1).trim();

        NivelLogEnum level = NivelLogEnum.valueOf(nivelStr.toUpperCase());

        return new LogEntry(timestamp, level, mensagem);
    }

    /**
     * Lê o arquivo do caminho informado e retorna apenas os logs com nível ERROR.
     */
    public List<LogEntry> extrairErrosDoArquivo(Path caminhoArquivo) throws IOException {
        try (Stream<String> linhas = Files.lines(caminhoArquivo)) {
            return linhas
                    .filter(linha -> !linha.isBlank())
                    .map(this::parsearLinha)
                    .filter(log -> log.level() == NivelLogEnum.ERROR)
                    .toList();
        }
    }

    /**
     * Agrupa e conta a quantidade de logs por nível a partir de um arquivo.
     */
    public Map<NivelLogEnum, Long> contarLogsPorNivel(Path caminhoArquivo) throws IOException {
        try (Stream<String> linhas = Files.lines(caminhoArquivo)) {
            return linhas
                    .filter(linha -> !linha.isBlank())
                    .map(this::parsearLinha)
                    .collect(Collectors.groupingBy(LogEntry::level, Collectors.counting()));
        }
    }
}