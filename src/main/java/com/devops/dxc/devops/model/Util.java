package com.devops.dxc.devops.model;

import com.devops.dxc.devops.service.UF;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    private UF uf;

    /**
     * Método para cacular el 10% del ahorro en la AFP. Las reglas de negocio se
     * pueden conocer en
     * https://www.previsionsocial.gob.cl/sps/preguntas-frecuentes-nuevo-retiro-seguro-10/
     * 
     * @param ahorro
     * @return
     */
    public long getDxc(long ahorro) {
        if (((ahorro * 0.1) / getUf()) > 150) {
            return (long) (150 * getUf());
        } else if ((ahorro * 0.1) <= 1000000 && ahorro >= 1000000) {
            return (long) 1000000;
        } else if (ahorro <= 1000000) {
            return (long) ahorro;
        } else {
            return (long) (ahorro * 0.1);
        }
    }

    /**
     * Método que retorna el valor de la UF. Este método debe ser refactorizado por
     * una longegración a un servicio
     * que retorne la UF en tiempo real. Por ejemplo mindicador.cl
     * 
     * @return
     */
    public long getUf() {
        return uf.getUf();
    }

    public long getImpuesto(long sueldo, long ahorro) {

        if (sueldo < 1500000) {
            return 0;
        }

        Long ingresosTotales = sueldo * 12 + getDxc(ahorro);

        // tabla con valores de
        // https://www.sii.cl/valores_y_fechas/renta/2022/personas_naturales.html
        float impuesto = 0.0f;
        if (ingresosTotales > 8775702 && (ingresosTotales < 19501560)) {
            impuesto = 0.04f * ingresosTotales - 351028;
        } else if (ingresosTotales >= 19501560 && (ingresosTotales < 32502600)) {
            impuesto = 0.08f * ingresosTotales - 1131090;
        } else if (ingresosTotales >= 32502600 && (ingresosTotales < 45503640)) {
            impuesto = 0.135f * ingresosTotales - 2918733;
        } else if (ingresosTotales >= 45503640 && (ingresosTotales < 58504680)) {
            impuesto = 0.230f * ingresosTotales - 7241579;
        } else if (ingresosTotales >= 58504680 && (ingresosTotales < 78006240)) {
            impuesto = 0.304f * ingresosTotales - 11570925;
        } else if (ingresosTotales >= 78006240 && (ingresosTotales < 201516120)) {
            impuesto = 0.35f * ingresosTotales - 15159212;
        } else if (ingresosTotales >= 201516120) {
            impuesto = 0.4f * ingresosTotales - 25235018;
        }

        return (long) impuesto;
    }

    public long saldoRestante(long ahorro, long sueldo) {
        return ahorro - getDxc(ahorro);
    }

}
