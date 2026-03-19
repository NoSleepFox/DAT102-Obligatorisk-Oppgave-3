package com.oppgaver.binaersok;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class sammenligneSokeTid {

    public static void main(String[] args) {

        int storelse = 10;
        int start = 4;
        int steg = 3;

        int tall = start;
        System.out.println("Kontroll: ");
        for (int i = 0;i<storelse; i++){

            System.out.print(tall + " ");

            tall = (tall + steg) % storelse;
        }

        int antElement = 100000;
        int antSok = 10000;
        int maksTall = 1000000;

        HashSet<Integer> hashSet = new HashSet<>();
        Integer[] tabell = new Integer[antElement];

        int Tall = 376;

        for (int i = 0; i < antElement; i++) {
            hashSet.add(Tall);
            tabell[i] = Tall;
            Tall = (Tall + 45713) % maksTall;

        }
        Arrays.sort(tabell);

        Integer[] soketall = new Integer[antSok];
        Random tilfeldig = new Random();

        for (int i = 0; i < antSok; i++) {
            soketall[i] = tilfeldig.nextInt(maksTall);
        }
        int funnetHash = 0;
        long startHash = System.nanoTime();

        for (int i = 0; i < antSok; i++) {
            if (hashSet.contains(soketall[i])) {
                funnetHash++;
            }
        }

    long sluttHash = System.nanoTime();

    int funnetTabell = 0;
    long startTabell = System.nanoTime();

    for (int i = 0; i<antSok; i++){
        if (Arrays.binarySearch(tabell, soketall[i])>=0){
            funnetTabell++;
        }
    }

    long sluttTabell = System.nanoTime();

    long tidHash = sluttHash - startHash;
    long tidTabell = sluttTabell - startTabell;

        System.out.println();
        System.out.println("Resultat av søk: ");
        System.out.println("Funne i HashSet: " +funnetHash);
        System.out.println("Tid brukt på HashSet-søk: " + tidHash + " ns");
        System.out.println("Tid brukt på HashSet-søk: " + (tidHash / 1_000_000.0));

        System.out.println();

        System.out.println("Funne med binærsøk i tabell: " + funnetTabell);
        System.out.println("Tid brukt på binærsøk: " + tidTabell + " ns");
        System.out.println("Tid brukt på binærsøk: " + (tidTabell / 1_000_000.0) + " ms");

}
}
