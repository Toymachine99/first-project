package com.myProg.simulator;

import com.myProg.entitati.Autor;
import com.myProg.entitati.Carte;
import com.myProg.entitati.Editura;
import com.myProg.utils.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Simulator simulator = new Simulator();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege optiunea 1-12");
        System.out.println("Optiunea 1 - inserarea unui autor nou");
        System.out.println("Optiunea 2 - inserarea unei edituri noi");
        System.out.println("Optiunea 3 - inserarea unei carti noi");
        System.out.println("Optiunea 4 - schimbarea numelui si prenumelui unui autor");
        System.out.println("Optiunea 5 - schimbarea numelui editurii");
        System.out.println("Optiunea 6 - afisarea tuturor cartilor");
        System.out.println("Optiunea 7 - afisarea autorului dupa nume si prenume");
        System.out.println("Optiunea 8 - afisarea varstei si numelui tuturor autorilor dupa titlui cartii");
        System.out.println("Optiunea 9 - afisarea tuturor cartilor cu genul dorit");
        System.out.println("Optiunea 10 - afisarea celei mai vechi carti si numele si anii de axperienta a autorilor ce au contribuit la scrieerea ei");
        System.out.println("Optiunea 11 - stergerea editurii alese");
        System.out.println("Optiunea 12 - verificarea disponibilitatii cartii dorite");
        System.out.println("EXTRA ");
        System.out.println("Optiunea 13 - schimbarea titlului cartii");
        System.out.println("Optiunea 14 - cea mai noua carte");
        System.out.println("Optiunea 15 - cartea cu cele mai multe pagini");
        System.out.println("Optiunea 16 - verificare stadiului de experienta la autorul dorit");
        System.out.println("Optiunea 17 - cea mai buna recenzie la editura");
        System.out.println("Optiunea 18 - care cu cei mai multi autori");
        System.out.println("Optiunea 19 - cautare carte");
        System.out.println("Optiunea 20 - inserare autor din exterior");
        System.out.println("Optiunea 21 - inserare editura din exterior");
        System.out.println("Optiunea 22 - Ordonare alfabetica a Prenumelor");
        System.out.println("Optiunea 0 - oprirea programului");
        int option = scanner.nextInt();
        do {
            if (option == 1) {
                Autor autor = simulator.inserareAutor();
                session.persist(autor);
            }
            if (option == 2) {
                Editura editura = simulator.inserareEditura();
                session.persist(editura);
            }
            if (option == 3) {
                Carte carte = simulator.inserareCarte(session);
                session.persist(carte);
            }
            if (option == 4) {
                simulator.updateAutor(session);
            }
            if (option == 5) {
                simulator.updateEditura(session);
            }
            if (option == 6) {
                simulator.afisareCarti(session);
            }
            if (option == 7) {
                simulator.afisareAutorDupaNumeSiPrenume(session);
            }
            if (option == 8) {
                simulator.afisareAutoriLaCarte(session);
            }
            if (option == 9) {
                simulator.afisareEdituraSiNumeleCartiilorDupaGen(session);
            }
            if (option == 10) {
                simulator.afisareaCeaMaiVecheCarte(session);
            }
            if (option == 11) {
                simulator.stergereDinEditura(session);
            }
            if (option == 12) {
                simulator.verificareCarte(session);
            }
            //EXTRA
            if (option == 13) {
                simulator.updateCarteStatus(session);
            }
            if (option == 14) {
                simulator.afisareCeaMaiNouaCarte(session);
            }
            if (option == 15) {
                simulator.afisareaCarteCuCeleMaiMultePagini(session);
            }
            if (option == 16) {
                simulator.verificareStandardExperienta(session);
            }
            if (option == 17) {
                simulator.ceaMaiBunaRecenzieLaEditura(session);
            }
            if (option == 18) {
                simulator.afisareCarteCuCeiMaiMultiAutori(session);
            }
            if (option == 19) {
                simulator.cautareCarte(session);
            }
            if (option == 20) {
                simulator.inserareAutoriDinFisier(session);
            }
            if (option == 21) {
                simulator.inserareEdituraDinFisier(session);
            }
            if (option == 22) {
                simulator.afisareInordineAlfabeticaPrenume(session);
            }
            System.out.println("Alege optiunea dorita in continuare");
            System.out.println("Optiunea 0 - oprirea programului");
            option = scanner.nextInt();
        } while (option != 0);
        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
