package br.feevale.entities;

import br.feevale.orm.Entity;

public class Brand implements Entity{
    private int id;
    private String name;
    private String country;
    
    public Brand(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Brand(String name, String country) {
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    

}
