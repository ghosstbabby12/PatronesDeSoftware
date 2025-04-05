package com.security.network.service;

import org.springframework.stereotype.Service;

@Service
public class URLAnalyzerService {

    public String analizarUrl(String url) {
        // Aquí deberías colocar la lógica real de análisis usando Google Safe Browsing.
        // Por ahora, haremos un ejemplo sencillo:

        if (url.contains("malware")) {
            return "❌ URL maliciosa";
        } else {
            return "✅ URL segura";
        }
    }
}
