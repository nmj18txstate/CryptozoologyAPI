package com.cryptozoologyAPI.cryptozoologyapi.Service;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Animal updateAnimal(Long id, Animal animal) {

        Animal oldAnimal = cryptozoologyRepository.findById(id).get();
        oldAnimal.setMood(animal.getMood());
        return cryptozoologyRepository.save(oldAnimal);
        //return cryptozoologyRepository.findById();
    }
}
