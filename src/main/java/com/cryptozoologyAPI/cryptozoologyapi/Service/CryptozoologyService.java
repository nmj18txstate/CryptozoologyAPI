package com.cryptozoologyAPI.cryptozoologyapi.Service;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptozoologyService {
 @Autowired
 CryptozoologyRepository cryptozoologyRepository;

    public Animal addAnimal(Animal animal) {
        return cryptozoologyRepository.save(animal);
    }

    public List<Animal> getallAnimals() {
        return cryptozoologyRepository.findAll();
    }
}
