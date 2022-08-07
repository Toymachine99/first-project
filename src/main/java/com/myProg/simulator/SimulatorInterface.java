package com.myProg.simulator;

import com.myProg.entitati.Autor;
import com.myProg.entitati.Carte;
import com.myProg.entitati.Editura;
import org.hibernate.Session;

import java.io.IOException;

public interface SimulatorInterface {

    Carte inserareCarte(Session session);

    Editura inserareEditura();

    Autor inserareAutor();

    void updateAutor(Session session);

    void updateEditura(Session session);

    void updateCarteStatus(Session session);

    void afisareCarti(Session session);

    void afisareAutorDupaNumeSiPrenume(Session session);

    void afisareAutoriLaCarte(Session session);

    void afisareEdituraSiNumeleCartiilorDupaGen(Session session);

    void afisareaCeaMaiVecheCarte(Session session);

    void stergereDinEditura(Session session);

    void verificareCarte(Session session);

    void afisareCeaMaiNouaCarte(Session session);

    void afisareaCarteCuCeleMaiMultePagini(Session session);

    void verificareStandardExperienta(Session session);

    void ceaMaiBunaRecenzieLaEditura(Session session);

    void afisareCarteCuCeiMaiMultiAutori(Session session);

    void cautareCarte(Session session);

    void inserareAutoriDinFisier(Session session) throws IOException;

    void inserareEdituraDinFisier(Session session) throws IOException;

}
