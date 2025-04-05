package com.security.network.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.network.service.URLAnalyzerService;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*")
public class SeguridadController {

    private final URLAnalyzerService analyzerService;
    private final List<String> urls = new ArrayList<>();

    @Autowired
    public SeguridadController(URLAnalyzerService analyzerService) {
        this.analyzerService = analyzerService;
    }

    // Agregar una URL a la lista
    @PostMapping("/add")
    public String agregarUrl(@RequestBody Map<String, String> request) {
        String url = request.get("url");
        if (url != null && !url.isEmpty()) {
            urls.add(url);
            return "✅ URL agregada: " + url;
        } else {
            return "⚠️ URL no válida.";
        }
    }

    // Eliminar una URL de la lista
    @DeleteMapping("/delete")
    public String eliminarUrl(@RequestBody Map<String, String> request) {
        String url = request.get("url");
        if (urls.remove(url)) {
            return "🗑️ URL eliminada: " + url;
        } else {
            return "❌ URL no encontrada.";
        }
    }

    // Ejecutar análisis de todas las URLs agregadas
    @GetMapping("/run")
    public Map<String, String> ejecutarAnalisis() {
        Map<String, String> resultados = new LinkedHashMap<>();
        for (String url : urls) {
            String resultado = analyzerService.analizarUrl(url);
            resultados.put(url, resultado);
        }
        return resultados;
    }

    // Obtener la lista de URLs actuales
    @GetMapping("/list")
    public List<String> listarUrls() {
        return urls;
    }

    // Analizar una URL específica
    @PostMapping("/analyze")
    public String analizarUrl(@RequestBody Map<String, String> request) {
        String url = request.get("url");
        if (url != null && !url.isEmpty()) {
            return analyzerService.analizarUrl(url);
        } else {
            return "⚠️ URL no válida.";
        }
    }
}
