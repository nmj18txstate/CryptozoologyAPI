package com.cryptozoologyAPI.cryptozoologyapi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Animal {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String type;
    private String mood;

    public Animal(String name, String type,String mood) {
        this.name = name;
        this.type = type;
        this.mood = mood;
    }

    public Animal() {
    }

    public String getMood() {return mood;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
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


    public void setMood(String mood)
    {
        this.mood = mood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id.equals(animal.id) && name.equals(animal.name) && type.equals(animal.type) && mood.equals(animal.mood);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, mood);
    }
}
