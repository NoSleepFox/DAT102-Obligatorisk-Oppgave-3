package com.oppgaver;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaSetToMengdeTest {
    private JavaSetToMengde<Integer> mengde1;
    private JavaSetToMengde<Integer> mengde2;

    @BeforeEach
    public void setup() {
        mengde1 = new JavaSetToMengde<>();
        mengde2 = new JavaSetToMengde<>();

        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);

        mengde2.leggTil(3);
        mengde2.leggTil(4);
        mengde2.leggTil(5);
    }

    @Test
    public void testErTom() {
        JavaSetToMengde<Integer> tom = new JavaSetToMengde<>();
        assertTrue(tom.erTom());
        tom.leggTil(10);
        assertFalse(tom.erTom());
    }

    @Test
    public void testLeggTilOgAntall() {
        JavaSetToMengde<Integer> m = new JavaSetToMengde<>();
        m.leggTil(1);
        m.leggTil(2);
        m.leggTil(2);

        assertEquals(2, m.antallElementer());
    }

    @Test
    public void testInneholder() {
        assertTrue(mengde1.inneholder(1));
        assertFalse(mengde1.inneholder(10));
    }

    @Test
    public void testFjern() {
        Integer fjernet = mengde1.fjern(2);
        assertEquals(2, fjernet);
        assertFalse(mengde1.inneholder(2));
        assertEquals(2, mengde1.antallElementer());
    }

    @Test
    public void testErDelmengdeAv() {
        JavaSetToMengde<Integer> del = new JavaSetToMengde<>();
        del.leggTil(1);
        del.leggTil(2);

        assertTrue(del.erDelmengdeAv(mengde1));
        assertFalse(mengde1.erDelmengdeAv(del));
    }

    @Test
    public void testErDisjunkt() {
        JavaSetToMengde<Integer> m = new JavaSetToMengde<>();
        m.leggTil(7);
        m.leggTil(8);

        assertTrue(m.erDisjunkt(mengde1));
        assertFalse(mengde1.erDisjunkt(mengde2));
    }

    @Test
    public void testSnitt() {
        MengdeADT<Integer> snitt = mengde1.snitt(mengde2);

        assertTrue(snitt.inneholder(3));
        assertEquals(1, snitt.antallElementer());
    }

    @Test
    public void testUnion() {
        MengdeADT<Integer> union = mengde1.union(mengde2);

        assertTrue(union.inneholder(1));
        assertTrue(union.inneholder(2));
        assertTrue(union.inneholder(3));
        assertTrue(union.inneholder(4));
        assertTrue(union.inneholder(5));

        assertEquals(5, union.antallElementer());
    }

    @Test
    public void testMinus() {
        MengdeADT<Integer> minus = mengde1.minus(mengde2);

        assertTrue(minus.inneholder(1));
        assertTrue(minus.inneholder(2));
        assertFalse(minus.inneholder(3));

        assertEquals(2, minus.antallElementer());
    }

    @Test
    public void testTilTabell() {
        Object[] tabell = mengde1.tilTabell();

        assertEquals(3, tabell.length);
        assertTrue(tabell[0] != null);
    }
}
