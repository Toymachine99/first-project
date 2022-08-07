package com.myProg.entitati;

import jakarta.persistence.*;

@Entity
@Table(name = "EdituraTable")
public class Editura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id" , unique = true , nullable = false)
    private int id;

    @Column(name = "nume", unique = false, nullable = false)
    private String nume;

    @Column(name = "anDeAparitie", unique = false , nullable = false)
    private int anAparitia;

    @Column(name = "ranking",unique = false,nullable = true)
    private int ranking;

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

    public int getAnAparitia() {
        return anAparitia;
    }

    public void setAnAparitia(int anAparitia) {
        this.anAparitia = anAparitia;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "Editura{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", anAparitia=" + anAparitia +
                ", ranking=" + ranking +
                '}';
    }
}
