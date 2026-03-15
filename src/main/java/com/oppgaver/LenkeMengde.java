package com.oppgaver;

public class LenkeMengde<T> implements MengdeADT<T> {

    private class Node {
        public T data;
        public Node neste;

        public Node(T data) {
            this.data = data;
            this.neste = null;
        }
    }

    private Node forste;
	private int antall;
	
	public LenkeMengde() {
		forste = null;
		antall = 0;
	}

    @Override
    public boolean erTom() {
        return !(antall > 0);
    }

    private boolean sookEtter(Node node, T element) {
        if (node == null) { return false; }

        if (node.data.equals(element)) { return true; }

        return sookEtter(node.neste, element);

    }

    @Override
    public boolean inneholder(T element) {
        if (erTom()) { return false; }

        if (forste.data.equals(element)) {
            return true;
        } else {
            return sookEtter(forste, element);
        }
    }

    @Override
    public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
        Node sookMengde = forste;
        while (true) {
            if (sookMengde == null) { return false; }
            
            if (!(annenMengde.inneholder(sookMengde.data))) {
                sookMengde = sookMengde.neste;
            } else { return true; }
        }
    }

    @Override
    public boolean erLik(MengdeADT<T> annenMengde) {
        if (erTom() || annenMengde.erTom()) { return false; }

        Node sookMengde = forste;
        boolean ulik = false;
        while (true) {
            if (ulik) { return false; }

            if (sookMengde == null ) { return true; }

            if (!(annenMengde.inneholder(sookMengde.data))) {
                ulik = true;
            }

            sookMengde = sookMengde.neste;
        }
    }

    @Override
    public boolean erDisjunkt(MengdeADT<T> annenMengde) {
        return !erDelmengdeAv(annenMengde);
    }

    @Override
    public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
        LenkeMengde<T> returnMengde = new LenkeMengde<>();
        Node sookMengde = forste;

        while (true) {
            if (sookMengde == null) { return returnMengde; }

            if (annenMengde.inneholder(sookMengde.data)) {
                returnMengde.leggTil(sookMengde.data);
            }

            sookMengde = sookMengde.neste;
        }
    }

    @Override
    public MengdeADT<T> union(MengdeADT<T> annenMengde) {
        LenkeMengde<T> returnMengde = new LenkeMengde<>();
        returnMengde.leggTilAlleFra(this);
        returnMengde.leggTilAlleFra(annenMengde);
        return returnMengde;
    }

    @Override
    public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
        LenkeMengde<T> returnMengde = new LenkeMengde<>();
        Node sookMengde = forste;

        while (true) {
            if (sookMengde == null) { return returnMengde; }

            if (!(annenMengde.inneholder(sookMengde.data))) {
                returnMengde.leggTil(sookMengde.data);
            }

            sookMengde = sookMengde.neste;
        }
    }

    @Override
    public void leggTil(T element) {
        if (!inneholder(element)) {
            Node nyNode = new Node(element);
            nyNode.neste = forste;
            forste = nyNode;
            antall++;
        }
    }

    @Override
    public void leggTilAlleFra(MengdeADT<T> annenMengde) {
        if (!(annenMengde.erTom())){
            T[] tabell = annenMengde.tilTabell();
                
            for (T element : tabell) {
                leggTil(element);
            }
        }
    }

    @Override
    public T fjern(T element) {
        Node denneNode = forste;
        Node forrigeNode = null;

        while (denneNode != null) {
            
            if (denneNode.data.equals(element)) {

                if (forrigeNode == null) {
                    forste = denneNode.neste;
                } else {
                    forrigeNode.neste = denneNode.neste;
                }

                antall--;
                return denneNode.data;

            }

            forrigeNode = denneNode;
            denneNode = denneNode.neste;

        }

        return null;
    }

    @Override
    public T[] tilTabell() {
        T[] tabell = (T[]) new Object[antall];

        Node denneNode = forste;
        int i = 0;

        while (denneNode != null) {
            tabell[i++] = denneNode.data;
            denneNode = denneNode.neste;
        }

        return tabell;
    }

    @Override
    public int antallElementer() {
        return antall;
    }
}
