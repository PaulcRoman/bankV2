package com.company.Controller;

import com.company.model.Angajat;
import com.company.model.Client;
import com.company.model.Cont;
import com.company.model.Persoana;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formattable;
import java.util.Scanner;

public class ControlPersoane {

    private ArrayList<Persoana> listaPersoane;
    private String path;

    public ControlPersoane(String path){
        listaPersoane = new ArrayList<>();
        this.path = path;
        load();
    }

    public void add(Persoana persoana){
        listaPersoane.add(persoana);
    }

    public void afisare(){
        for (int i = 0; i< listaPersoane.size(); i++){
            System.out.println(listaPersoane.get(i));
        }
    }

    public void load(){

        try {

            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){

                String linie = scanner.nextLine();

                if (linie.equals("")==false){

                    String functie = linie.split(",")[3];

                    switch (functie){
                        case "client":
                            listaPersoane.add(new Client(linie));
                            break;

                        case "angajat":
                            listaPersoane.add(new Angajat(linie));
                            break;
                    }
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getPozitie(int id){

        for (Persoana p : listaPersoane){
            if (p.getId() == id){
                return listaPersoane.indexOf(p);
            }
        }
        return -1;
    }

    public String updateNume(int id, String nume){
        int pozitie = getPozitie(id);

        if (pozitie != -1){

            Persoana p  = listaPersoane.get(pozitie);
            p.setNume(nume);

        }
        return nume;
    }

    public String updatePrenume(int id, String prenume){
        int pozitie = getPozitie(id);

        if (pozitie != -1){
             Persoana p = listaPersoane.get(pozitie);
             p.setPrenume(prenume);
        }
        return prenume;
    }

    public String updateFunctie(int id, String functie){
        int pozitie = getPozitie(id);

        if (pozitie != -1){

            Persoana p = listaPersoane.get(pozitie);
            p.setFunctie(functie);
        }
        return functie;
    }

    public String updateEmail(int id, String email){
        int pozitie = getPozitie(id);

        if (pozitie != -1){
            Persoana p = listaPersoane.get(pozitie);
            p.setEmail(email);
        }
        return email;
    }

    public String updateParola(int id, String parola){
        int pozitie = getPozitie(id);

        if (pozitie != -1){
            Persoana p = listaPersoane.get(pozitie);
            p.setParola(parola);
        }
        return parola;
    }

    public boolean delete(int id){
        int pozitie = getPozitie(id);

        if (pozitie != -1){
            listaPersoane.remove(pozitie);
            return true;
        }
        return false;
    }

    public void afisareNumarPersoaneFunctii(){
        int client = 0;
        int angajat = 0;

        for (Persoana p : listaPersoane){

            if (p instanceof Client){
                client++;
            }else if (p instanceof Angajat){
                angajat++;
            }
        }
        System.out.println(String.format("Clienti: %d \nAngajat: %d", client , angajat));
    }

    public void sortByName(){
        for (int i = 0; i< listaPersoane.size()-1;i++){
            for ( int j = i +1; j < listaPersoane.size(); i++){

                if (listaPersoane.get(i).getNume().compareTo(listaPersoane.get(j).getNume())>0){
                    Persoana aux = listaPersoane.get(i);
                    listaPersoane.set(i, listaPersoane.get(j));
                    listaPersoane.set(j, aux);
                }
            }
        }
    }

    public void save(){

        try {

            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(this);
            printWriter.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public String toString(){
        String text = "";

        for (Persoana p : listaPersoane){

            text += p + "\n";
        }
        return text;
    }

    public void clear(){
        listaPersoane.clear();
    }

    public int nextId(){

        if (listaPersoane.size()>0){

            return listaPersoane.get(listaPersoane.size()-1).getId()+1;
        }
        return -1;
    }

    public Persoana login(String email, String parola){

        for (Persoana p : listaPersoane){

            if (p.getEmail().equals(email) && p.getParola().equals(parola)){
                return p;
            }
        }
        return null;
    }
}
