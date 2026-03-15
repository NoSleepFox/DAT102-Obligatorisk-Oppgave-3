package com.oppgaver;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LenkeMengdeTest {

    private LenkeMengde<Integer> mengde1;
    private LenkeMengde<Integer> mengde2;

    @BeforeEach
    void setup() {
        mengde1 = new LenkeMengde<>();
        mengde2 = new LenkeMengde<>();
    }

    @Test
    void testErTom() {
        assertTrue(mengde1.erTom());
        mengde1.leggTil(1);
        assertFalse(mengde1.erTom());
    }

    @Test
    void testAntall() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(2);

        assertEquals(2, mengde1.antallElementer());
    }

    @Test
    void testInneholder() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        assertTrue(mengde1.inneholder(1));
        assertTrue(mengde1.inneholder(2));
        assertFalse(mengde1.inneholder(3));
    }

    @Test
    void testFjern() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        Integer fjernet = mengde1.fjern(1);

        assertEquals(1, fjernet);
        assertFalse(mengde1.inneholder(1));
        assertEquals(1, mengde1.antallElementer());
    }

    @Test
    void testUnion() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        mengde2.leggTil(2);
        mengde2.leggTil(3);

        MengdeADT<Integer> union = mengde1.union(mengde2);

        assertTrue(union.inneholder(1));
        assertTrue(union.inneholder(2));
        assertTrue(union.inneholder(3));
        assertEquals(3, union.antallElementer());
    }

    @Test
    void testSnitt() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        mengde2.leggTil(2);
        mengde2.leggTil(3);
        mengde2.leggTil(4);

        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);

        assertTrue(snitt.inneholder(2));
        assertTrue(snitt.inneholder(3));
        assertFalse(snitt.inneholder(1));
    }

    @Test
    void testMinus() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        mengde2.leggTil(2);

        MengdeADT<Integer> minus = mengde1.minus(mengde2);

        assertTrue(minus.inneholder(1));
        assertTrue(minus.inneholder(3));
        assertFalse(minus.inneholder(2));
    }

    @Test
    void testErLik() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        mengde2.leggTil(1);
        mengde2.leggTil(2);

        assertTrue(mengde1.erLik(mengde2));
    }

    @Test
    void testErDisjunkt() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        mengde2.leggTil(3);
        mengde2.leggTil(4);

        assertTrue(mengde1.erDisjunkt(mengde2));
    }

    @Test
    void testTilTabell() {
        mengde1.leggTil(1);
        mengde1.leggTil(2);

        Object[] tabell = mengde1.tilTabell();

        assertEquals(2, tabell.length);
    }
}
