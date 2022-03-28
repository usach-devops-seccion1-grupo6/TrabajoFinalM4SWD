package com.devops.dxc.devops.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilTest {

    @Autowired
    Util util;
    
    @Test
    void testGetDxc() {
        assertEquals(1000000, util.getDxc(1200000));
        assertEquals(4000000, util.getDxc(40000000));
        assertEquals(800000, util.getDxc(800000));
    }

    @Test
    void testGetImpuesto() {
        assertEquals(0, util.getImpuesto(1400000, 1000000));
        assertEquals(684910, util.getImpuesto(1700000, 23000000));
        assertEquals(2184267, util.getImpuesto(2900000, 30000000));
        assertEquals(4892692, util.getImpuesto(4000000, 60000000));
    }

    @Test
    void testGetUf() {
        Assertions.assertThat(util.getUf()).isGreaterThan(0);
    }

    @Test
    void testSaldoRestante() {
        Assertions.assertThat(util.saldoRestante(1200000, 1400000)).isGreaterThan(0);
        assertEquals(200000, util.saldoRestante(1200000, 1400000));
    }
}
