package com.myProg.entitati;

import jakarta.persistence.*;

@Entity
@Table(name = "AutorTable")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id" , unique = true , nullable = false)
    private int id;


    @Column(name = "Nume", unique = false , nullable = false)
    private String nume;

    @Column(name = "Prenume", unique = false , nullable = false)
    private String Prenume;

    @Column(name = "Varsta", unique = false , nullable = false)
    private int varsta;

    @Column(name = "AniDeExperienta" , unique = false , nullable = false)
    private int aniDeExperienta;

    @Column(name = "Gen", unique = false,nullable = false)
    private String gen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getAniDeExperienta() {
        return aniDeExperienta;
    }

    public void setAniDeExperienta(int aniDeExperienta) {
        this.aniDeExperienta = aniDeExperienta;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", varsta=" + varsta +
                ", aniDeExperienta=" + aniDeExperienta +
                ", gen='" + gen + '\'' +
                '}';
    }
}
