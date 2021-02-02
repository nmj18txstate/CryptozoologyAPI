package com.cryptozoologyAPI.cryptozoologyapi.Controller;

import com.cryptozoologyAPI.cryptozoologyapi.Model.Animal;
import com.cryptozoologyAPI.cryptozoologyapi.Model.AnimalRequest;
import com.cryptozoologyAPI.cryptozoologyapi.Model.Habitat;
import com.cryptozoologyAPI.cryptozoologyapi.Service.CryptozoologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zoo")

public class CryptozoologyController {

    @Autowired
    CryptozoologyService cryptozoologyService;

    @PostMapping("/animal")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal addAnimal(@RequestBody Animal animal){
     return cryptozoologyService.addAnimal(animal);
    }

    @GetMapping("/animal")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getallAnimals(){
        return cryptozoologyService.getallAnimals();
    }

    @PutMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal giveTreat(@PathVariable Long id,@RequestBody Animal animal ){
        return cryptozoologyService.updateAnimal(id,animal);
    }

    @PostMapping("/habitat")
    @ResponseStatus(HttpStatus.CREATED)
    public Habitat createHabitat(@RequestBody Habitat habitat){
        return cryptozoologyService.createHabitat(habitat);
    }

    @PutMapping(path = "/habitat/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.OK)
    public Habitat updateHabitat(@PathVariable Long id,@RequestBody AnimalRequest animalRequest) throws Exception{
        return  cryptozoologyService.updateHabitat(id, animalRequest.getId());
    }

    @GetMapping("/habitat/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Habitat getHabitat(@PathVariable Long id){
        return cryptozoologyService.getHabitatById(id);
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal getAnimal(@PathVariable Long id){
        return cryptozoologyService.getAnimalById(id);
    }

    @GetMapping("/animal-type-mood")
    public List<Animal> getAnimalByTypeAndMood(@RequestParam(value = "mood", required = false) String mood, @RequestParam(value = "type",required = false) String type){
        return cryptozoologyService.getAnimalByTypeAndMood(mood, type);

    }
}
