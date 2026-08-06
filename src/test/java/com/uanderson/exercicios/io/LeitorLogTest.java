package com.uanderson.exercicios.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LeitorLogTest {

    private LeitorLog leitorLog;

    @BeforeEach
    void setUp() {
        leitorLog = new LeitorLog();
    }

    @Test
    @DisplayName("Deve parsear uma linha de log corretamente")
    void deveParsearLinhaComSucesso() {
        String linha = "2026-08-06 14:30:00 [ERROR] Conexao recusada";
        LogEntry log = leitorLog.parsearLinha(linha);

        assertEquals("2026-08-06 14:30:00", log.timestamp());
        assertEquals(NivelLogEnum.ERROR, log.level());
        assertEquals("Conexao recusada", log.mensagem());
    }

    @Test
    @DisplayName("Deve filtrar apenas os erros de um arquivo de log simulado")
    void deveExtrairErrosDoArquivo(@TempDir Path pastaTemporaria) throws IOException {
        Path arquivoLog = pastaTemporaria.resolve("app.log");
        List<String> conteudo = List.of(
                "2026-08-06 10:00:00 [INFO] Sistema iniciado",
                "2026-08-06 10:05:00 [ERROR] Falha de conexao com banco",
                "2026-08-06 10:10:00 [WARNING] Memoria acima de 80%",
                "2026-08-06 10:15:00 [ERROR] OutOfMemoryError detectado"
        );
        Files.write(arquivoLog, conteudo);

        List<LogEntry> erros = leitorLog.extrairErrosDoArquivo(arquivoLog);

        assertEquals(2, erros.size());
        assertTrue(erros.stream().allMatch(log -> log.level() == NivelLogEnum.ERROR));
    }

    @Test
    @DisplayName("Deve agrupar e contar os logs por nível corretamente")
    void deveContarLogsPorNivel(@TempDir Path pastaTemporaria) throws IOException {
        Path arquivoLog = pastaTemporaria.resolve("app.log");
        List<String> conteudo = List.of(
                "2026-08-06 10:00:00 [INFO] Sistema iniciado",
                "2026-08-06 10:01:00 [INFO] Usuario autenticado",
                "2026-08-06 10:05:00 [ERROR] Falha de banco"
        );
        Files.write(arquivoLog, conteudo);

        Map<NivelLogEnum, Long> contagem = leitorLog.contarLogsPorNivel(arquivoLog);

        assertEquals(2L, contagem.get(NivelLogEnum.INFO));
        assertEquals(1L, contagem.get(NivelLogEnum.ERROR));
        assertNull(contagem.get(NivelLogEnum.WARNING));
    }
}