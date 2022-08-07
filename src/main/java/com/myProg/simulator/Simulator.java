package com.myProg.simulator;

import com.myProg.entitati.Autor;
import com.myProg.entitati.Carte;
import com.myProg.entitati.Editura;
import com.myProg.exceptii.NuAFostGasitaCartea;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator implements SimulatorInterface {

    @Override
    public Carte inserareCarte(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Inserare carte");
        System.out.println("Introdu numele la carte");
        String titluCarte = scanner.next();
        Carte carte = new Carte();
        System.out.println("Alege an publicare carte");
        Integer anPublicare = scanner.nextInt();
        System.out.println("Alege gen");
        String gen = scanner.next();
        System.out.println("Alege numar de pagini");
        Integer noPagini = scanner.nextInt();
        System.out.println("Alege status-ul");
        boolean status = scanner.nextBoolean();
        carte.setGen(gen);
        carte.setTitlu(titluCarte);
        carte.setAn(anPublicare);
        carte.setNumbarDePagini(noPagini);
        carte.setStatus(status);
        //EDITURA
        System.out.println("Introdu editura");
        String editura = scanner.next();
        Query from_editura = session.createQuery("FROM Editura where nume = :num");
        from_editura.setParameter("num", editura);
        List resultList = from_editura.getResultList();
        if (resultList.size() > 0) {
            Editura result = (Editura) resultList.get(0);
            carte.setEditura(result);
        } else {
            System.out.println("Editura ceruta nu exista");
        }
        //AUTOR
        System.out.println("Introdu numarul de autori");
        int number = scanner.nextInt();
        List<Autor> autorList = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            System.out.println("Inserare numele la autor");
            String autor = scanner.next();
            Query from_autor = session.createQuery("FROM Autor where nume = :num");
            from_autor.setParameter("num", autor);
            List resultList1 = from_autor.getResultList();
            if (resultList1.size() == 0) {
                System.out.println("Autorul nu a fost gasit");
            } else {
                autorList.add((Autor) resultList1.get(0));
            }
        }
        carte.setAutorList(autorList);
        return carte;
    }

    @Override
    public Editura inserareEditura() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege nume editura");
        String numeEditura = scanner.next();
        System.out.println("Alege an aparitie");
        Integer anAparitie = scanner.nextInt();
        System.out.println("Alege ranking");
        Integer ranking = scanner.nextInt();
        Editura editura = new Editura();
        editura.setNume(numeEditura);
        editura.setAnAparitia(anAparitie);
        editura.setRanking(ranking);
        return editura;
    }

    @Override
    public Autor inserareAutor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege nume autor");
        String numeAutor = scanner.next();
        System.out.println("Alege prenume autor");
        String prenumeAutor = scanner.next();
        System.out.println("Alege varsta");
        int varsta = scanner.nextInt();
        System.out.println("Alege anExp");
        int anExp = scanner.nextInt();
        System.out.println("Introdu genul");
        String gen = scanner.next();
        Autor autor = new Autor();
        autor.setNume(numeAutor);
        autor.setPrenume(prenumeAutor);
        autor.setVarsta(varsta);
        autor.setAniDeExperienta(anExp);
        autor.setGen(gen);
        return autor;
    }

    @Override
    public void updateAutor(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege numele  autorul care doresti sa schimbi");
        String input = scanner.next();
        System.out.println("Alege prenumele la autor");
        String inputTwo = scanner.next();
        Query from_autor = session.createQuery("from Autor where nume = :nume and Prenume = :Prenume");
        from_autor.setParameter("nume", input);
        from_autor.setParameter("Prenume", inputTwo);
        List list = from_autor.list();
        if (list.size() > 0) {
            Autor result = (Autor) list.get(0);
            System.out.println("Alege numele nou");
            String nume = scanner.next();
            System.out.println("Alege prenumele nou");
            String prenume = scanner.next();
            result.setNume(nume);
            result.setPrenume(prenume);
            session.update(result);
        } else {
            System.out.println("Autorul nu exista in baza de date");
        }
    }

    @Override
    public void updateEditura(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege editura pe  care il doresti sa schimbi");
        String input = scanner.next();
        Query from_editura = session.createQuery("from Editura where nume = :editura ");
        from_editura.setParameter("editura", input);
        List list = from_editura.list();
        if (list.size() > 0) {
            Editura result = (Editura) list.get(0);
            System.out.println("Alege editura");
            String editura = scanner.next();
            result.setNume(editura);
            session.update(result);
        } else {
            System.out.println("Editura cautata nu exista");
        }
    }

    @Override
    public void updateCarteStatus(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Alege numele  cartii care doresti sa schimbi");
        String input = scanner.next();
        Query from_carte = session.createQuery("from Carte where titlu=: nume");
        from_carte.setParameter("nume", input);
        List list = from_carte.list();
        if (list.size() > 0) {
            Carte result = (Carte) list.get(0);
            System.out.println("Schimba status-ul -> TRUE OR FALSE");
            boolean status = scanner.nextBoolean();
            result.setStatus(status);
            session.update(result);
        } else {
            throw new NuAFostGasitaCartea("Nu a fost gasita cartea");
        }
    }

    @Override
    public void afisareCarti(Session session) {
        Query from_carte = session.createQuery("FROM Carte");
        List list = from_carte.list();
        list.forEach(System.out::println);
    }

    @Override
    public void afisareAutorDupaNumeSiPrenume(Session session) {
        System.out.println("Introdu numele la autor");
        Scanner scanner = new Scanner(System.in);
        String nume = scanner.next();
        System.out.println("Introdu prenumele la autor");
        String prenume = scanner.next();
        Query from_autor = session.createQuery("FROM Autor where nume=:num and Prenume=:pre");
        from_autor.setParameter("num", nume);
        from_autor.setParameter("pre", prenume);
        List list = from_autor.list();
        list.forEach(System.out::println);
    }

    @Override
    public void afisareAutoriLaCarte(Session session) {
        System.out.println("Introdu numele la carte pe care doresti sa o cauti");
        Scanner scanner = new Scanner(System.in);
        String numeCarte = scanner.next();
        Query query = session.createQuery("FROM Carte where titlu= :titlu");
        query.setParameter("titlu", numeCarte);
        List list = query.list();
        Carte result = (Carte) list.get(0);
        System.out.println("Autori care au participat la scrierea cartii " + result.getTitlu() + " sunt : ");
        for (Autor autor : result.getAutorList()) {
            System.out.println(autor.getNume() + " " + autor.getVarsta());
        }
    }

    @Override
    public void afisareEdituraSiNumeleCartiilorDupaGen(Session session) {
        System.out.println("Insereaza GEN-ul cartillor");
        Scanner scanner = new Scanner(System.in);
        String gen = scanner.next();
        Query query = session.createQuery("FROM Carte where gen =:gen");
        query.setParameter("gen", gen);
        List<Carte> list = query.list();
        list.forEach(System.out::println);
    }

    @Override
    public void afisareaCeaMaiVecheCarte(Session session) {
        Query from_carte = session.createQuery("FROM Carte");
        List<Carte> list = from_carte.list();
        int min = 9999;
        for (Carte carte : list) {
            if (carte.getAn() < min) {
                min = carte.getAn();
            }
        }
        for (Carte carte : list) {
            if (carte.getAn() == min) {
                System.out.println(carte);
            }
        }
    }

    @Override
    public void stergereDinEditura(Session session) {
        System.out.println("Introdu numele la editura pe care doresti sa il stergi");
        Scanner scanner = new Scanner(System.in);
        String numeEditura = scanner.next();
        Query from_editura = session.createQuery("FROM Editura where nume=:nume");
        from_editura.setParameter("nume", numeEditura);
        if (from_editura.list().size() > 0) {
            Editura result = (Editura) from_editura.list().get(0);
            session.remove(result);
            System.out.println(result.getNume() + " a fost sters cu succes din baza de date ");
        } else {
            throw new NuAFostGasitaCartea("Editura nu exista in baza de date");
        }
    }

    @Override
    public void verificareCarte(Session session) {
        System.out.println("Introdu numele la carte");
        Scanner scanner = new Scanner(System.in);
        String numeCarte = scanner.next();
        Query query = session.createQuery("FROM Carte where titlu= :titlu");
        query.setParameter("titlu", numeCarte);
        List<Carte> list = query.list();
        if (list.size() > 0) {
            for (Carte element : list) {
                System.out.println("Cartea cu numele " + element.getTitlu() + "are stats-ul " + element.isStatus());
            }
        } else {
            throw new NuAFostGasitaCartea("Cartea nu a fost gasita");
        }
    }

    public void afisareCeaMaiNouaCarte(Session session) {
        Query query = session.createQuery("FROM Carte");
        List<Carte> list = query.getResultList();
        int anDefault = 0;
        System.out.println("Cele mai noi carti sunt : ");
        for (Carte element : list) {
            if (element.getAn() > anDefault) {
                anDefault = element.getAn();
                System.out.println(anDefault);
            }
            if (element.getAn() == anDefault) {
                System.out.println(element.getTitlu());
            }
        }
    }

    @Override
    public void afisareaCarteCuCeleMaiMultePagini(Session session) {
        Query query = session.createQuery("FROM Carte");
        List<Carte> list = query.list();
        int numarPaginiMax = 0;
        for (Carte carte : list) {
            if (carte.getNumbarDePagini() > numarPaginiMax) {
                numarPaginiMax = carte.getNumbarDePagini();
            }
        }
        System.out.println("Numarul maxim de pagini este " + numarPaginiMax + " la cartea ");
        for (Carte carte : list) {
            if (carte.getNumbarDePagini() == numarPaginiMax) {
                System.out.print(carte.getTitlu());
            }
        }
    }

    @Override
    public void verificareStandardExperienta(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdu numele autorului dorit");
        String autorDorit = scanner.next();
        Query query = session.createQuery(" FROM Autor where nume =:nume");
        query.setParameter("nume", autorDorit);
        System.out.println("Introdu anii de experienta");
        int anExpDorit = scanner.nextInt();
        List list = query.list();
        Autor result = (Autor) list.get(0);
        int anExp = result.getAniDeExperienta();
        if (anExp > anExpDorit) {
            System.out.println("Autorul cu numele : " + autorDorit + " are mai multi ani experienta decat : " + anExpDorit);
        } else
            System.out.println("Autorul cu numele : " + autorDorit + " nu are mai multi ani experienta decat : " + anExpDorit);
    }

    @Override
    public void ceaMaiBunaRecenzieLaEditura(Session session) {
        Query query = session.createQuery("FROM Editura");
        List<Editura> list = query.list();
        int ranking = 0;
        for (Editura editura : list) {
            if (editura.getRanking() > ranking) {
                ranking = editura.getRanking();
            }
        }
        System.out.println("Numarul de cel mai bun ranking este: " + ranking + " la editurile : ");
        for (Editura editura : list) {
            if (editura.getRanking() == ranking) {
                System.out.println(editura.getNume());
            }
        }
    }

    @Override
    public void afisareCarteCuCeiMaiMultiAutori(Session session) {
        Query from_carte = session.createQuery("FROM Carte");
        List<Carte> list = from_carte.list();
        int maxAutor = 0;
        for (Carte carte : list) {
            if (carte.getAutorList().size() > maxAutor) {
                maxAutor = carte.getAutorList().size();
            }
        }
        for (Carte carte : list) {
            if (carte.getAutorList().size() == maxAutor) {
                System.out.println("Numarul maxim de autori este " + maxAutor + " la cartea " + carte.getTitlu());
            }
        }
    }

    @Override
    public void cautareCarte(Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introdu titlul la cartea dorita");
        String titluCautare = scanner.next();
        Query query = session.createQuery("FROM Carte where titlu=:titlu");
        query.setParameter("titlu", titluCautare);
        List list = query.list();
        if (list.size() > 0) {
            Carte result = (Carte) list.get(0);
            System.out.println(result);
        } else {
            throw new NuAFostGasitaCartea("Nu a fost gasita cartea ceruta");
        }
    }

    @Override
    public void inserareAutoriDinFisier(Session session) throws IOException {
        List<Autor> autorList = new ArrayList<>();
        Path path = Path.of("C:", "Users", "Marius", "Desktop", "autors.txt");
        List<String> strings = Files.readAllLines(path);
        for (String string : strings) {
            String[] split = string.split(",");
            Autor autor = new Autor();
            autor.setNume(split[0]);
            autor.setPrenume(split[1]);
            autor.setAniDeExperienta(Integer.parseInt(split[2]));
            autor.setGen(split[3]);
            autor.setVarsta(Integer.parseInt(split[4]));
            autorList.add(autor);
        }
        for (Autor autor : autorList) {
            session.persist(autor);
        }
    }

    @Override
    public void inserareEdituraDinFisier(Session session) throws IOException {
        List<Editura> edituraList = new ArrayList<>();
        Path path = Path.of("C:", "Users", "Marius", "Desktop", "editura.txt");
        List<String> strings = Files.readAllLines(path);
        for (String stringElement : strings) {
            String[] split = stringElement.split(",");
            Editura editura = new Editura();
            editura.setAnAparitia(Integer.parseInt(split[0]));
            editura.setNume(split[1]);
            editura.setRanking(Integer.parseInt(split[2]));
            edituraList.add(editura);
        }
        for (Editura editura : edituraList) {
            session.persist(editura);
        }
    }

    public void afisareInordineAlfabeticaPrenume(Session session){
        Query from_autor_order_by_Prenume = session.createQuery("From Autor ORDER BY Prenume");
                List <Autor> autorList = from_autor_order_by_Prenume.list();
        System.out.println(autorList);
    }




}


