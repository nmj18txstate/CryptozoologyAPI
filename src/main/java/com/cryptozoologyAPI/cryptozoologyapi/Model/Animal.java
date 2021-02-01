package com.cryptozoologyAPI.cryptozoologyapi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

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


    public void setMood(String mood)
    {
        this.mood = mood;
    }
}
