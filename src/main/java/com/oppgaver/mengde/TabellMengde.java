package com.oppgaver.mengde;

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T>{
    
    private static final int DEFAULT_KAPASITET = 10;

	private T[] tabell;
	private int antall;

	public TabellMengde() {
		this(DEFAULT_KAPASITET);
	}

	@SuppressWarnings("unchecked")
	public TabellMengde(int kapasitet) {
		tabell = (T[]) new Object[kapasitet];
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T anEntry) {
        for (int i = 0; i < antall; i++) {
            if (anEntry.equals(tabell[i])) {
                return true;
            }
        }
        return false;
	}
    
    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if(!annenMengde.inneholder(tabell[i])){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        for (int i = 0; i < antall; i++) {
            if(annenMengde.inneholder(tabell[i])){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        return erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde){
        TabellMengde<T> returnMengde = new TabellMengde<>();

        for (int i = 0; i < antall; i++) {
            if (annenMengde.inneholder(tabell[i])) {
                returnMengde.leggTil(tabell[i]);
            }
        }
        return returnMengde;

    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde){
        TabellMengde<T> returnMengde = new TabellMengde<>();

        returnMengde.leggTilAlleFra(this);
        returnMengde.leggTilAlleFra(annenMengde);
        return returnMengde;

    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde){
        TabellMengde<T> returnMengde = new TabellMengde<>();

        for (int i = 0; i < antall; i++) {
            if (!annenMengde.inneholder(tabell[i])) {
                returnMengde.leggTil(tabell[i]);
            }
        }
        return returnMengde;

    }

    @Override
    public void leggTil(T element) {
        if (!(inneholder(element))) {
            if (antall >= tabell.length) {
                tabell = Arrays.copyOf(tabell, antall * 2);
            }
            tabell[antall] = element;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        T[] annenMengdeTabell = annenMengde.tilTabell();
        for (T element : annenMengdeTabell) {
            leggTil(element);
        }
    }

    @Override
    public T fjern(T element){
        T temp = null;
        for (int i = 0; i < antall; i++) {
            if (tabell[i].equals(element)) {
                temp = tabell[i];
                tabell[i] = tabell[antall - 1];
                tabell[antall - 1] = null;
                antall--;
                return temp;
            }
        }
        return temp;
    }

    @Override
    public T[] tilTabell() {
		return Arrays.copyOf(tabell, antall);
	}

    @Override
    public int antallElementer() {
        return antall;
    }

}
