package com.company.Controller;

import com.company.model.Enrolment;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlEnrolment {

    private ArrayList<Enrolment> listEnrolments;

    private String path;

    public ControlEnrolment(String path){
        listEnrolments = new ArrayList<>();
        this.path = path;
        load();
    }

    public void add(Enrolment enrolment){
        listEnrolments.add(enrolment);
    }

    public void afisare(){

        for (int i = 0; i<listEnrolments.size();i++){
            System.out.println(listEnrolments.get(i));

        }
    }

    public void load(){

        try {

            File file = new File(path);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){

                String linie = scanner.nextLine();

                if (linie.equals("")==false){

                    listEnrolments.add(new Enrolment(linie));
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public int getPozitie(int id){
        for (Enrolment e : listEnrolments){

            if (e.getId() == id){
                return listEnrolments.indexOf(e);
            }
        }
        return -1;
    }

    public int updateAccountId(int id, int accountID){
        int pozitie = getPozitie(id);

        if (pozitie != -1){

            Enrolment e = listEnrolments.get(pozitie);

            e.setAccountId(accountID);

        }
        return accountID;
    }

    public int updatePersinId(int id, int personId){
        int pozitie = getPozitie(id);

        if (pozitie != -1){

            Enrolment e = listEnrolments.get(pozitie);
            e.setPersonId(personId);
        }
        return personId;
    }

    public int updateID(int pozitie, int id){
        int index = getPozitie(pozitie);

        if (index != -1){
            Enrolment e  = listEnrolments.get(index);
            e.setId(id);
        }
        return id;
    }

    public boolean delete(int id){
        int pozitie = getPozitie(id);

        if (pozitie != -1) {
            listEnrolments.remove(pozitie);
            return true;
        }
        return false;
    }

    public void clear(){
        listEnrolments.clear();
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

        for (Enrolment e : listEnrolments){

            text += e+"\n";
        }
        return text;
    }

    public ArrayList<Enrolment> afisareDupaId(int idClient){
        ArrayList<Enrolment> enrolments = new ArrayList<>();

        for (Enrolment e : listEnrolments){
            if (e.getPersonId() == idClient){
                enrolments.add(e);
            }
        }
        return enrolments;
    }

    public int nextId(){

        if (listEnrolments.size()>0){
            return listEnrolments.get(listEnrolments.size() -1).getAccountId() +1;
        }

        return 1;
    }

}
