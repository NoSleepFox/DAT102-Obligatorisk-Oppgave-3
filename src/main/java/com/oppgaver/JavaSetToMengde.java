package com.oppgaver;

import java.util.HashSet;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {
    
    private Set<T> mengde;

    public JavaSetToMengde() {
        mengde = new HashSet<T>();
    }

    @Override
    public boolean erTom() {
        return mengde.isEmpty();
    }

    @Override
    public boolean inneholder(T element) {
        return mengde.contains(element);
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        T[] tabell = this.tilTabell();
        for (T element : tabell) {
            if (!annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (this.antallElementer() != annenMengde.antallElementer()) {
            return false;
        }
        return this.erDelmengdeAv(annenMengde);
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        T[] tabell = this.tilTabell();
        for (T element : tabell) {
            if (annenMengde.inneholder(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> returnMengde = new JavaSetToMengde<>();

        for (T element : mengde) {
            if (annenMengde.inneholder(element)) {
                returnMengde.leggTil(element);
            }
        }

        return returnMengde;
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> returnMengde = new JavaSetToMengde<>();

        returnMengde.leggTilAlleFra(this);
        returnMengde.leggTilAlleFra(annenMengde);

        return returnMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        JavaSetToMengde<T> returnMengde = new JavaSetToMengde<>();

        for (T element : mengde) {
            if (!annenMengde.inneholder(element)) {
                returnMengde.leggTil(element);
            }
        }

        return returnMengde;
    }

    @Override
    public void leggTil(T element) {
        mengde.add(element);
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] tabell = annenMengde.tilTabell();
        for (T element : tabell) {
            mengde.add(element);
        }
    }

    @Override
    public T fjern(T element) {
        if (mengde.remove(element)) {
            return element;
        }
        return null;
    }

    @Override
    public T[] tilTabell() {
        return (T[]) mengde.toArray(new Object[mengde.size()]);
    }


    @Override
    public int antallElementer() {
        return mengde.size();
    }

}
