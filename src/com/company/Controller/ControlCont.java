package com.company.Controller;

import com.company.model.Cont;
import com.company.model.ContCurent;
import com.company.model.ContEconomii;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ControlCont {

    private ArrayList<Cont> listaConturi;
    private String path;
//    private String user;

    public ControlCont(String path){
        this.path = path;
        listaConturi = new ArrayList<>();
//        this.user = user;
        load();
    }

    public void add(Cont cont){
        listaConturi.add(cont);
    }

    public void afisare(){
        for (int i = 0; i< listaConturi.size(); i++){
            System.out.println(listaConturi.get(i));
        }
    }

    public void load(){

        try {

            File file = new File(path);

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()){

                String line = scanner.nextLine();

                if (line.equals("")==false){

                    String tipCont = line.split(",")[2];

                    switch (tipCont){

                        case "curent":
                            listaConturi.add(new ContCurent(line));
                            break;
                        case "economii":
                            listaConturi.add(new ContEconomii(line));
                            break;

                    }

                }

            }


        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public int getIndex(int id){

        for (Cont c : listaConturi){

            if (c.getId() == id){
                return listaConturi.indexOf(c);
            }

        }
        return -1;
    }


    public int updateNumarCont(int id, int numarCont){

        int pozitie = getIndex(id);

        if (pozitie != -1){

            Cont c = listaConturi.get(pozitie);

            c.setNumarCont(numarCont);
        }
        return numarCont;

    }

    public String updateTipCont(int id, String tipCont){
        int pozitie = getIndex(id);

        if (pozitie != -1){

            Cont c = listaConturi.get(pozitie);

            c.setTipCont(tipCont);

        }

        return tipCont;
    }

    public double updateBalanta(int id, double balanta){
        int pozitie = getIndex(id);

        if (pozitie != -1){

            Cont c = listaConturi.get(pozitie);

            c.setBalanta(balanta);

        }

        return balanta;
    }

    public boolean delete(int id){
        int pozitie = getIndex(id);

        if (pozitie != -1){

            listaConturi.remove(pozitie);
            return true;

        }
        return false;
    }

    public int getSize(){
        return listaConturi.size();
    }

    public void tipuriDeCont(){

        int contCurent = 0;
        int contEconimii=0;

        for (Cont c : listaConturi){

            if (c instanceof ContCurent){
                contCurent++;
            }else if (c instanceof ContEconomii){
                contEconimii++;
            }
        }

        System.out.println(String.format("Cont curent: %d\nCont economii: %d ", contCurent, contEconimii));

    }

    public void sortByTipCont(){

        for (int i = 0; i<listaConturi.size()-1; i++){
            for (int j = i+1; j<listaConturi.size();j++){

                if (listaConturi.get(i).getTipCont().compareTo(listaConturi.get(j).getTipCont())>0){

                    Cont aux = listaConturi.get(i);
                    listaConturi.set(i, listaConturi.get(j));
                    listaConturi.set(j, aux);

                }

            }
        }

    }

    public Cont getByNumber(int number){

        for (Cont c : listaConturi){

            if (c.getNumarCont() == number ){
                return c;
            }

        }
        return null;

    }

    public void save(){

        try {

            File file  = new File(path);
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
//        String text = "";
//
//        for (int i = 0; i<listaConturi.size()-1; i++){
//
//            text += listaConturi.get(i)+"\n";
//        }
//
//        text += listaConturi.get(listaConturi.size()-1);
//
//        return text;
//
        String text = "";

        for (Cont c : listaConturi) {

            text += c + "\n";

        }

        return text;
    }

    public int nextId(){

        if (listaConturi.size() >0){

            return listaConturi.get(listaConturi.size()-1).getNumarCont() + 1;

        }

        return -1;
    }

    public void clear(){
        listaConturi.clear();
    }

    public boolean availableCont(int number){

        for (Cont c : listaConturi){

            if (c.getNumarCont() == number){
                return false;
            }
        }
        return true;

    }

    public int random(){
        return (int) Math.floor(Math.random() * 100 +1);
    }

    public int nextAvailableAccount(){

        int rand = random();

        while (availableCont(rand)==false){
            rand = random();
        }
        return rand;
    }

    public int tipCont(String tip){

        int cont = 0;

        for (Cont c : listaConturi){

            if (c.getTipCont().equals(tip)){
                cont++;
            }

        }
        return cont;
    }
}
