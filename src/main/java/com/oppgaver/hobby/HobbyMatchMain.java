package com.oppgaver.hobby;

import com.oppgaver.mengde.MengdeADT;


public class HobbyMatchMain {

    public static double match(Person a, Person b){
        MengdeADT<String> hobbyA = a.getHobbyer();
        MengdeADT<String> hobbyB = b.getHobbyer();

        MengdeADT<String> felles = hobbyA.snitt(hobbyB);
        MengdeADT<String> kunHosA = hobbyA.minus(hobbyB);
        MengdeADT<String> kunHosB = hobbyB.minus(hobbyA);
        MengdeADT<String> totalt = hobbyA.union(hobbyB);

        int antallFelles = felles.antallElementer();
        int antallKunHosA = kunHosA.antallElementer();
        int antallKunHosB = kunHosB.antallElementer();
        int antallTotalt = totalt.antallElementer();

        if (antallTotalt == 0){
            return 0.0;
        }
        return (double) (antallFelles - (antallKunHosA + antallKunHosB)) / antallTotalt;

    }
    public static void main(String[]args){
        Person Arne = new Person("Arne","sakt","sykkling","venner","data");
        Person Simen = new Person("Simen", "sykling", "data","film", "venner");
        Person Chris = new Person("Chris", "byen","drikka","gambling","gaming");

        double ab = match(Arne,Simen);
        double ac = match(Arne,Chris);
        double bc = match(Simen,Chris);

        System.out.println("Match Arne og Simen" + ab);
        System.out.println("Match Arne og Chris" + ac);
        System.out.println("Match Simen og Chris" + bc);

        System.out.println();
        System.out.println("Sjekk symmetri");
        System.out.println("Match(Arne, Simen): "+ match(Arne,Simen));
        System.out.println("Match(Simen, Arne): "+ match(Simen,Arne));

        System.out.println();
        System.out.println("Sjekk selv-match: ");
        System.out.println("Match(Arne, Arne) " + match(Arne, Arne));

        Person best1 = Arne;
        Person best2 = Simen;
        double bestScore = ab;

        if(ac > bestScore){
            bestScore = ac;
            best1 = Arne;
            best2 = Chris;
        }
        if (bc> bestScore){
            bestScore = bc;
            best1 = Simen;
            best2 = Chris;
        }
        System.out.println();
        System.out.println("Best match er: "+ best1+ " og "+ best2+ " med score "+ bestScore );
    }

}
