package br.feevale.entities;

import br.feevale.orm.Entity;

public class Vehicle implements Entity {
    private int id;
    private String name;
    private int year;
    private int km;
    private int value_in_cents;
    private int id_brand;

    // nullable
    private String joinNameMarca;

    public Vehicle(int id, String name, int year, int km, int value_in_cents, int id_marca) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.km = km;
        this.value_in_cents = value_in_cents;
        this.id_brand = id_marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getValue_in_cents() {
        return value_in_cents;
    }

    public void setValue_in_cents(int value_in_cents) {
        this.value_in_cents = value_in_cents;
    }

    public int getId_brand() {
        return id_brand;
    }

    public void setId_brand(int id_marca) {
        this.id_brand = id_marca;
    }

    public String getJoinNameMarca() {
        return joinNameMarca;
    }

    public void setJoinNameMarca(String joinNameMarca) {
        this.joinNameMarca = joinNameMarca;
    }

    
}
