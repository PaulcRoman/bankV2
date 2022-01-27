package com.company.model;

public class Client extends Persoana{

    public Client(int id, String nume, String prenume, String functie, String email, String parola){
        super(id, nume, prenume,"client",email,parola);
    }

    public Client(String text){
        this(Integer.parseInt(text.split(",")[0]), text.split(",")[1], text.split(",")[2], text.split(",")[3],
                text.split(",")[4], text.split(",")[5]);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object o){

        Client c = (Client) o;

        return super.equals(c);

    }

//    @Override
//    public int compareTo(Client c){
//        return super.compareTo(c);
//    }

}
