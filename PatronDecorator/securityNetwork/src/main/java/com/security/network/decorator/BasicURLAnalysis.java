package com.security.network.decorator;

public class BasicURLAnalysis implements URLAnalyzer {

    private final String url;

    public BasicURLAnalysis(String url) {
        this.url = url;
    }

    @Override
    public String analizar() {
        return "Análisis básico de URL: " + url;
    }
}
