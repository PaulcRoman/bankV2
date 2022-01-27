package com.company.model;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import javax.swing.*;

public class ContEconomii extends Cont{

    private double depozitContEconomii;
    private int perioadaInLuni;
    private double dobanda;

    public ContEconomii(int id, int numarcont, String tip, double balanta,double depozitContEconomii, int perioadaInLuni, double dobanda){
        super(id, numarcont, "economii", balanta);
        this.depozitContEconomii = depozitContEconomii;
        this.perioadaInLuni = perioadaInLuni;
        this.dobanda = dobanda;
    }

    public ContEconomii(String text){
        super(Integer.parseInt(text.split(",")[0]), Integer.parseInt(text.split(",")[1]), text.split(",")[2],
                Double.parseDouble(text.split(",")[3]));
        this.depozitContEconomii = Double.parseDouble(text.split(",")[4]);
        this.perioadaInLuni = Integer.parseInt(text.split(",")[5]);
        this.dobanda = Double.parseDouble(text.split(",")[6]);
    }

    public double getDepozitContEconomii() {
        return depozitContEconomii;
    }

    public void setDepozitContEconomii(double depozitContEconomii) {
        this.depozitContEconomii = depozitContEconomii;
    }

    public int getPerioadaInLuni() {
        return perioadaInLuni;
    }

    public void setPerioadaInLuni(int perioadaInLuni) {
        this.perioadaInLuni = perioadaInLuni;
    }

    public double getDobanda() {
        return dobanda;
    }

    public void setDobanda(double dobanda) {
        this.dobanda = dobanda;
    }

    @Override
    public String toString(){
        return super.toString()+","+this.depozitContEconomii+","+this.perioadaInLuni+","+this.dobanda;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof ContEconomii){

            ContEconomii ce = (ContEconomii) o;

            return super.equals(ce);
        }
        return false;
    }
}
