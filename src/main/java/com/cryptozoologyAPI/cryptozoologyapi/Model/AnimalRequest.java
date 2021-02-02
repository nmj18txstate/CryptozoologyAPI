package com.cryptozoologyAPI.cryptozoologyapi.Model;

public class AnimalRequest {

    private Long id;
    public AnimalRequest(){

    }
    public AnimalRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
