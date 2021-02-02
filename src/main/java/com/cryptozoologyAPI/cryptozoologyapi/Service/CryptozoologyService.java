package com.cryptozoologyAPI.cryptozoologyapi.Service;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Model.Habitat;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.CryptozoologyRepository;
import com.cryptozoologyAPI.cryptozoologyapi.Repository.HabitatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptozoologyService {
 @Autowired
 private CryptozoologyRepository cryptozoologyRepository;

 @Autowired
 private   HabitatRepository habitatRepository;

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

    public Habitat createHabitat(Habitat habitat) {
        return habitatRepository.save(habitat);
    }

    public Habitat updateHabitat(Long habitatId, Long animalId) throws Exception {
       Habitat habitat = habitatRepository.findById(habitatId).get();
       Animal animal = cryptozoologyRepository.findById(animalId).get();
       if(habitat.getAnimal()!=null)
       {
           return habitat;
       }else if(!habitat.getType().equals(animal.getType())) {
           animal.setMood("UnHappy");
           cryptozoologyRepository.save(animal);
          // throw new Exception();
       }else {
           habitat.setAnimal(animal);
       }
        return habitatRepository.save(habitat);
    }

    public Habitat getHabitatById(Long id) {
        return habitatRepository.findById(id).get();
    }

    public Animal getAnimalById(Long id) {
        return cryptozoologyRepository.findById(id).get();
    }

    public List<Animal> getAnimalByTypeAndMood(String mood, String type) {
//        List<Animal> animalList = cryptozoologyRepository.findAll();
//        List<Animal> resultList = animalList.stream()
//                .filter(a->a.getMood().equals(mood)&&a.getType().equals(type)).collect(Collectors.toList());
      //  return resultList;

        return cryptozoologyRepository.findByMoodAndType(mood,type);
    }
}
