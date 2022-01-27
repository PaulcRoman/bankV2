package com.company.model;

public class Angajat extends Persoana{

    public Angajat(int id, String nume, String prenume, String functie, String email, String parola){
        super(id, nume,prenume,"angajat",email,parola);
    }

    public Angajat(String text){
        this(Integer.parseInt(text.split(",")[0]), text.split(",")[1], text.split(",")[2],text.split(",")[3],
                text.split(",")[4], text.split(",")[5]);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        Angajat a = (Angajat) o;
        return super.equals(a);
    }

}
