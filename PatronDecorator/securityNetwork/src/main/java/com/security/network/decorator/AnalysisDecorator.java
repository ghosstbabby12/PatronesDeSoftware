package com.security.network.decorator;

public class AnalysisDecorator implements URLAnalyzer {

    protected final URLAnalyzer urlAnalyzer;
    private final boolean esMaliciosa;

    public AnalysisDecorator(URLAnalyzer urlAnalyzer, boolean esMaliciosa) {
        this.urlAnalyzer = urlAnalyzer;
        this.esMaliciosa = esMaliciosa;
    }

    @Override
    public String analizar() {
        String resultadoBase = urlAnalyzer.analizar();
        if (esMaliciosa) {
            return resultadoBase + "\nClasificación: Alta\nDescripción: URL maliciosa detectada.";
        } else {
            return resultadoBase + "\nClasificación: Baja\nDescripción: URL segura.";
        }
    }
}
