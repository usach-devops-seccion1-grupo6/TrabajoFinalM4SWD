package com.devops.dxc.devops.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {
    @Test
    void testGetDxc() {
        assertEquals(1000000, Util.getDxc(1200000));
        assertEquals(4000000, Util.getDxc(40000000));
        assertEquals(800000, Util.getDxc(800000));
    }

    @Test
    void testGetImpuesto() {
        assertEquals(0, Util.getImpuesto(1400000, 1000000));
        assertEquals(3472000, Util.getImpuesto(1700000, 23000000));
        assertEquals(8748000, Util.getImpuesto(2900000, 30000000));
        assertEquals(24840000, Util.getImpuesto(4000000, 60000000));
        
        //assertEquals("No", Util.getImpuesto(1500000));
    }

    @Test
    void testGetUf() {
        Assertions.assertThat(Util.getUf()).isGreaterThan(0);
    }

    @Test
    void testSaldoRestante() {
        Assertions.assertThat(Util.saldoRestante(1200000, 1400000)).isGreaterThan(0);
        assertEquals(200000, Util.saldoRestante(1200000, 1400000));
    }
}
