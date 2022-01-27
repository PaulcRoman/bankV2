package com.company.view;

import com.company.Controller.ControlCont;
import com.company.Controller.ControlEnrolment;
import com.company.Controller.ControlPersoane;
import com.company.model.Client;
import com.company.model.Persoana;

import java.nio.file.Path;
import java.util.Scanner;

public class ViewLogin {

    private ControlPersoane controlPersoane;
    private ControlCont controlCont;
    private ControlEnrolment controlEnrolment;
    private Scanner scanner;
    private Client client;

    public ViewLogin(){
        controlPersoane = new ControlPersoane(Path.of("src","com","company","resorces","persoane").toString());
        controlEnrolment = new ControlEnrolment(Path.of("src","com","company","resorces","enrolment").toString());
        controlCont = new ControlCont(Path.of("src","com","company","resorces","cont").toString());
        scanner = new Scanner(System.in);
    }

    public void meniu(){
        System.out.println("Apasati tasta 1 pentru a va loga");
        System.out.println("Apasati tasta 2 pentru a incheia sesiunea.");
    }

    public void go(){
        boolean running = true;

        while (running==true){
            meniu();

            int alegere = Integer.parseInt(scanner.nextLine());

            switch (alegere){
                case 1:
                    login();
                    break;
                case 2:
                    controlPersoane.save();
                    controlEnrolment.save();
                    controlCont.save();
                    System.out.println("La revedere!");
                    running = false;
                    break;
            }
        }
    }

    public void login(){
        System.out.println("Introduceti emailul");

        String email = scanner.nextLine();

        System.out.println("Introduceti parola");

        String parola = scanner.nextLine();

        Persoana persoana = controlPersoane.login(email, parola);

        if (persoana != null && persoana instanceof Client){
            ViewUsers viewUsers = new ViewUsers((Client) persoana);
            viewUsers.go();
        }else {
            System.out.println("Email sau parola invalide, va rugam incercati din nou!");
            go();
        }
    }
}
