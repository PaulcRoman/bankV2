package com.company.model;

public class Cont implements Comparable<Cont>{

    private int id;
    private int numarCont;
    private String tipCont;
    double balanta;


    public Cont(int id, int numarCont, String tipCont, double balanta){
        this.id = id;
        this.tipCont = tipCont;
        this.numarCont = numarCont;
        this.balanta = balanta;
    }

    public Cont(String text){
        this(Integer.parseInt(text.split(",")[0]), Integer.parseInt(text.split(",")[1]),text.split(",")[2],
                Double.parseDouble(text.split(",")[3]));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumarCont() {
        return numarCont;
    }

    public void setNumarCont(int numarCont) {
        this.numarCont = numarCont;
    }

    public String getTipCont() {
        return tipCont;
    }

    public void setTipCont(String tipCont) {
        this.tipCont = tipCont;
    }

    public double getBalanta() {
        return balanta;
    }

    public void setBalanta(double balanta) {
        this.balanta = balanta;
    }

    @Override
    public String toString(){
        return this.id+","+this.numarCont+","+this.tipCont+","+this.balanta;
    }

    @Override
    public boolean equals(Object o){
        Cont c = (Cont) o;

        if (c.getId() == id){
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Cont c){

        if (this.numarCont > c.numarCont){
            return 1;
        }else if (this.numarCont < c.numarCont){
            return -1;
        }else {
            return 0;
        }

    }
}
