package com.myProg.entitati;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CarteTable")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id" , unique = true , nullable = false)
    private int id;

    @Column(name = "Titlu" , unique = true , nullable = false)
    private String titlu;

    @Column(name = "AnPublicare" , unique = false , nullable = false)
    private int an;

    @Column(name = "Gen", unique = false , nullable = false)
    private String gen;

    @Column(name = "NumardePagini" , unique = false, nullable = false)
    private int numbarDePagini;

    @Column(name = "Status", unique = false, nullable = false)
    private boolean status;

    @OneToMany
    private List<Autor> autorList;

    @OneToOne
    private Editura editura;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getNumbarDePagini() {
        return numbarDePagini;
    }

    public void setNumbarDePagini(int numbarDePagini) {
        this.numbarDePagini = numbarDePagini;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Autor> getAutorList() {
        return autorList;
    }

    public void setAutorList(List<Autor> autorList) {
        this.autorList = autorList;
    }

    public Editura getEditura() {
        return editura;
    }

    public void setEditura(Editura editura) {
        this.editura = editura;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", an=" + an +
                ", gen='" + gen + '\'' +
                ", numbarDePagini=" + numbarDePagini +
                ", status='" + status + '\'' +
                ", autorList=" + autorList +
                ", editura=" + editura +
                '}';
    }
}
