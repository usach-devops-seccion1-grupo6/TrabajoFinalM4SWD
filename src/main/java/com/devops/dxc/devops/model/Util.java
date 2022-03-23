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

    public static String getImpuesto(int sueldo) {
        
        if(sueldo < 1500000){
            return "No"; 
        }
        
        Integer sueldoAnual = sueldo * 12;

        float impuesto = 0.0f;
        if (sueldoAnual > 17864280 && (sueldoAnual < 29773800)) {
            impuesto = 0.08f;
        } else if (sueldoAnual > 29700000 && (sueldoAnual < 41600000)) {
            impuesto = 0.135f;
        }

        return impuesto > 0 ? "Si" : "No";
    }

    public static int saldoRestante(int ahorro, int sueldo) {
        return ahorro - getDxc(ahorro);
    }

}
