package com.devops.dxc.devops.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class UF {
    Long uf;
    Calendar fecha;
    static UF instance = null;

    UF() {
        this.fecha = Calendar.getInstance();
        this.fecha.setTime(Date.from(Instant.now()));
        this.uf = this.getUfToday();
    }
    
    public Long getUf() {
        if (!isCurrUf()) {
            this.uf = this.getUfToday();
        }

        return this.uf;
    }
    
    private boolean isCurrUf() {
        Calendar cNow = Calendar.getInstance();
        cNow.setTime(Date.from(Instant.now()));
        return cNow.get(Calendar.DAY_OF_YEAR) == this.fecha.get(Calendar.DAY_OF_YEAR);
    }
    
    private long getUfToday() {
        JsonObject jsonObject = null;

        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://mindicador.cl/api/"))
                    .header("Accept", "application/json").build();
            HttpResponse<String> httpResponse = HttpClient.newBuilder().build()
                    .send(httpRequest, BodyHandlers.ofString());

            jsonObject = JsonParser.parseString(httpResponse.body()).getAsJsonObject();

        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }

        return jsonObject.get("uf").getAsJsonObject().get("valor").getAsLong();
    }
}
