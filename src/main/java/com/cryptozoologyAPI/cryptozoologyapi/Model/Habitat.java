package com.cryptozoologyAPI.cryptozoologyapi.Model;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Habitat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String type;

    @OneToOne(cascade = ALL)
    private Animal animal;

    public Habitat(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public Habitat(){

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Long getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }
}
