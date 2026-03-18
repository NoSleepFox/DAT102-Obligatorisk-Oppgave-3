package com.oppgaver.hobby;

import com.oppgaver.mengde.JavaSetToMengde;
import com.oppgaver.mengde.MengdeADT;

import java.util.Objects;

public class Person {

    private String navn;
    private MengdeADT<String> hobbyer;

    public Person(String navn, String... hobbyer){
        this.navn = navn;
        this.hobbyer = new JavaSetToMengde<>();

        for (String hobby : hobbyer){
            this.hobbyer.leggTil(hobby);
        }
    }
    public String getNavn(){
        return navn;
    }

    public MengdeADT<String> getHobbyer() {
        return hobbyer;
    }
    @Override
    public String toString(){
        return navn;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(navn, person.navn);
    }

    @Override
    public int hashCode(){
        return Objects.hash(navn);
    }
}
