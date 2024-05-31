package br.feevale.entities;

import br.feevale.orm.Entity;

public class Country implements Entity{

    private int id;
    private String name;
    private String sigla;

    public Country(int id, String name, String sigla) {
        this.id = id;
        this.name = name;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    

    
    

}
