package com.devops.dxc.devops.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Util {
    /**
     * Método para cacular el 10% del ahorro en la AFP. Las reglas de negocio se
     * pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @return
     */
    public static int getDxc(int ahorro) {
        if (((ahorro * 0.1) / getUf()) > 150) {
            return (int) (150 * getUf());
        } else if ((ahorro * 0.1) <= 1000000 && ahorro >= 1000000) {
            return (int) 1000000;
        } else if (ahorro <= 1000000) {
            return (int) ahorro;
        } else {
            return (int) (ahorro * 0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF. Este método debe ser refactorizado por
     * una integración a un servicio
     * que retorne la UF en tiempo real. Por ejemplo mindicador.cl
     * 
     * @return
     */
    public static int getUf() {

        JsonObject jsonObject = null;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://mindicador.cl/api/"))
                    .header("Accept", "application/json").build();
            HttpResponse<String> httpResponse = HttpClient.newBuilder().build()
                    .send(httpRequest, BodyHandlers.ofString());

            jsonObject = JsonParser.parseString(httpResponse.body()).getAsJsonObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.get("uf").getAsJsonObject().get("valor").getAsInt();
    }

    public static int getImpuesto(int sueldo, int ahorro) {

        if (sueldo < 1500000) {
            return 0;
        }

        Integer sueldoAnual = sueldo * 12;

        // tabla con valores de
        // https://www.sii.cl/valores_y_fechas/renta/2022/personas_naturales.html
        float impuesto = 0.0f;
        if (sueldoAnual > 8775702 && (sueldoAnual < 19501560)) {
            impuesto = 0.04f;
        } else if (sueldoAnual >= 19501560 && (sueldoAnual < 32502600)) {
            impuesto = 0.08f;
        } else if (sueldoAnual >= 32502600 && (sueldoAnual < 45503640)) {
            impuesto = 0.135f;
        } else if (sueldoAnual >= 45503640 && (sueldoAnual < 58504680)) {
            impuesto = 0.230f;
        } else if (sueldoAnual >= 58504680 && (sueldoAnual < 78006240)) {
            impuesto = 0.304f;
        } else if (sueldoAnual >= 78006240 && (sueldoAnual < 201516120)) {
            impuesto = 0.35f;
        } else if (sueldoAnual >= 201516120) {
            impuesto = 0.4f;
        }

        return (int) impuesto * (sueldoAnual + ahorro);
    }

    public static int saldoRestante(int ahorro, int sueldo) {
        return ahorro - getDxc(ahorro);
    }

}
