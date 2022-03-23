package com.devops.dxc.devops.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTest {
    @Test
    void testGetDxc() {
        //assertEquals();
    }

    @Test
    void testGetImpuesto() {
        assertEquals("No", Util.getImpuesto(1400000));
        assertEquals("Si", Util.getImpuesto(1700000));
        assertEquals("Si", Util.getImpuesto(1500000));
    }

    @Test
    void testGetUf() {
        Assertions.assertThat(Util.getUf()).isGreaterThan(0);
    }

    @Test
    void testSaldoRestante() {
        
    }
}
