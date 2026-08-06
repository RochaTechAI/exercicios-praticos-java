package com.uanderson.exercicios.io;

public record LogEntry(String timestamp, NivelLogEnum level, String mensagem) {
}