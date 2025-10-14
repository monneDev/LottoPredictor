package com.example.lottopredictor.regular.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Regular {

    @Id
    private int number;
    private int drawn;

    public Regular(){}

    public Regular(int number, int drawn) {
        this.number = number;
        this.drawn = drawn;
    }

    public int getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public int getDrawn() {return drawn;}

    public void setDrawn(int drawn) {this.drawn = drawn;}
}
