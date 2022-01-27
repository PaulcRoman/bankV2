package com.company.view;

import com.company.Controller.ControlCont;
import com.company.Controller.ControlEnrolment;
import com.company.Controller.ControlPersoane;
import com.company.model.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ViewUsers {

    private ControlEnrolment controlEnrolment;
    private ControlCont controlCont;
    private Client client;
//    private Cont cont;
    protected Scanner scanner;

    public ViewUsers(Client client){
        controlEnrolment = new ControlEnrolment(Path.of("src","com","company","resorces","enrolment").toString());
        controlCont = new ControlCont(Path.of("src","com","company","resorces","cont").toString());
        this.client = client;
//        this.cont = cont;
        scanner = new Scanner(System.in);
    }

    public void meniu(){
        System.out.println("==========Meniu User===========");
        System.out.println("Apasati tasta 1 pentru a vizializa conturile");
        System.out.println("Apasati tasta 2 pentru a adauga fonduri");
        System.out.println("Apasati tasta 3 pentru a retrage fonduri");
        System.out.println("Apasati tasta 4 pentru a transfera fonduri");
        System.out.println("Apasati tasta 5 pentru a crea un cont nou");

    }

    public void go(){
        meniu();

        boolean running = true;

        while (running){

            int alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere){
                case 1:
                    afisareConturi();
                    go();
                    break;
                case 2:
                    adaugareFonduri();
                    break;
                case 3:
                    retragereFonduri();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    newCont();
                    break;


            }

        }
    }

    public void afisareConturi(){
        ArrayList<Enrolment>enrolments = controlEnrolment.afisareDupaId(this.client.getId());

        for (Enrolment e : enrolments){
            System.out.println(e);
        }

    }

    public void adaugareFonduri(){

        System.out.println("Introduceti numarul contului:");
        afisareConturi();
        int cont = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceti suma pe care doriti sa o adaugati");
        double depozit = Double.parseDouble(scanner.nextLine());

        Cont cont1 = controlCont.getByNumber(cont);



        if (cont1 != null){
            cont1.setBalanta(cont1.getBalanta()+depozit);

            System.out.println("Suma de "+depozit+ " a fost adaugata in contul "+cont1.getId()+". Balanta contului "+cont1.getId()+ " este de: "+cont1.getBalanta());

            controlCont.save();
        }else {
            System.out.println("Cont nevalid, va rugam sa introduceti din nou!");
            go();
        }
    }

    public void retragereFonduri(){
        System.out.println("Introduceti numarul contului:");
        int contCurent = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceti suma pe care doriti sa o retrageti");
        double sumaRetrasa = Double.parseDouble(scanner.nextLine());

        Cont cont = controlCont.getByNumber(contCurent);

        if (cont != null  && sumaRetrasa < cont.getBalanta()){
            cont.setBalanta(cont.getBalanta()-sumaRetrasa);

            System.out.println("Retragere efectuata cu success. Balanta residuala pentru contul "+cont.getId()+" este de "+cont.getBalanta());

            controlCont.save();
        }else {
            if (sumaRetrasa > cont.getBalanta()){
                System.out.println("Fonduri insuficiente!");
                go();
            }else {

                    System.out.println("Numar cont inexistent!");
                    go();

            }
        }
    }

    public void transfer(){
        System.out.println("Introduceti numarul contului:");
        int cont  = Integer.parseInt(scanner.nextLine());

        System.out.println("Intrduceti numarul contului pentru transfer:");
        int contTransfer = Integer.parseInt(scanner.nextLine());

        System.out.println("Introduceti suma pe care doriti sa o transferati.");
        int sunaTransfer = Integer.parseInt(scanner.nextLine());

        Cont contDeTransfer = controlCont.getByNumber(cont);
        Cont contDeDepozit = controlCont.getByNumber(contTransfer);

        if (contDeTransfer !=null && contDeTransfer.getBalanta()>sunaTransfer && contDeDepozit !=null){
            contDeTransfer.setBalanta(contDeTransfer.getBalanta()-sunaTransfer);
            contDeDepozit.setBalanta(contDeDepozit.getBalanta()+sunaTransfer);

            System.out.println("Transferul s-a efectuat cu succsess. Balanta residuala in contul " +contDeTransfer.getId()+ " este de: "+contDeTransfer.getBalanta()+
                    " Noua balanta a contului "+contDeDepozit.getId()+" este de "+contDeDepozit.getBalanta());

        }else {
            System.out.println("Fonduri insuficiente!");
            go();
        }
    }

    public void newCont(){
        System.out.println("Introduceti tipul contului: curent sau economii");

        String tipCont = scanner.nextLine();

        switch (tipCont){
            case "curent":
                ContCurent contC = new ContCurent(controlCont.nextAvailableAccount(),controlCont.nextAvailableAccount(),"curent",0.00);

                this.controlCont.add(contC);
                controlCont.save();

                Enrolment enrolment = new Enrolment(controlEnrolment.nextId(),this.client.getId(),contC.getId());

                controlEnrolment.add(enrolment);

                controlEnrolment.save();

                System.out.println("S-a adaugat contul curent cu numarul: "+contC.getNumarCont());

                break;



            case "economii":
                ContEconomii contE = new ContEconomii(controlCont.nextAvailableAccount(),controlCont.nextAvailableAccount(), "econimii",
                        0.00, 0.00, 0, 0.00);
                this.controlCont.add(contE);

                controlCont.save();

                Enrolment enrolment1 = new Enrolment(controlEnrolment.nextId(),this.client.getId(),contE.getId());

                controlEnrolment.add(enrolment1);

                controlEnrolment.save();

                System.out.println("S-a adaugat contul economii cu numarul: "+contE.getNumarCont());

                break;
        }
    }

}
