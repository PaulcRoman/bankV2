package com.company.model;

public class Persoana implements Comparable<Persoana>{

    private int id;
    private String nume;
    private String prenume;
    private String functie;
    public String email;
    private String parola;

    public Persoana(int id, String nume, String prenume, String functie, String email, String parola){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.functie = functie;
        this.email = email;
        this.parola = parola;

    }

    public Persoana(String text){
        this(Integer.parseInt(text.split(",")[0]), text.split(",")[1], text.split(",")[2], text.split(",")[3],
                text.split(",")[4], text.split(",")[5]);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString(){
        return this.id+","+this.nume+","+this.prenume+","+this.functie+","+this.email+","+this.parola;
    }

    @Override
    public boolean equals(Object o){

        Persoana p = (Persoana) o;

        if (p.getEmail().equals(email)){
            return true;
        }
        return false;

    }

    @Override
    public int compareTo(Persoana persoana){

        if (this.id > persoana.id){
            return 1;
        }else if (this.id < persoana.id ){
            return -1;
        }else {
            return 0;
        }

    }
}
